package com.mcalzada.service;

import com.mcalzada.gateway.MarvelGateway;
import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiCharacterResponse.ApiCharacterResult;
import com.mcalzada.model.api.ApiComicsResponse;
import com.mcalzada.model.api.ApiCreatorResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.model.entity.Collaborator;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@EnableScheduling
public class APISynchronizerService
{

    private final MarvelGateway marvelGateway;
    private final CharacterService characterService;
    private final CollaboratorService collaboratorService;

    @Value("#{'${marvel.characters.sync}'.split(',')}")
    private List<String> requiredHeros;
    @Value("${marvel.api.public-api-key}")
    private String publicApiKey;
    @Value("${marvel.api.private-api-key}")
    private String privateApiKey;

    @Autowired
    public APISynchronizerService(MarvelGateway marvelGateway, CharacterService characterService,
          CollaboratorService collaboratorService)
    {
        this.marvelGateway = marvelGateway;
        this.characterService = characterService;
        this.collaboratorService = collaboratorService;
    }

    @Scheduled(cron = "${synchronization.crontab}")
    public void synchronize()
    {
        for (String hero : requiredHeros)
        {
            Long ts = Instant.now().getEpochSecond();
            ApiCharacterResponse apiCharacterResponse = this.marvelGateway.getCharacterByName(hero, publicApiKey, getHash(ts), ts.toString());
            for (ApiCharacterResult result : apiCharacterResponse.getApiCharacterData().getResults())
            {
                Character character = result.buildCharacter();
                this.synchronizeComics(character);
            }
        }
    }

    @Async
    public void synchronizeComics(Character character)
    {
        List<Comic> comics = new ArrayList<>();
        Long ts = Instant.now().getEpochSecond();
        ApiComicsResponse apiComicsResponse = this.marvelGateway.getComicsByCharacter(character.getId(), publicApiKey, getHash(ts), ts.toString());
        for (ApiComicsResponse.Results result : apiComicsResponse.getData().getResults())
        {
            Comic comic = result.buildComic();
            comics.add(comic);
            synchronizeCreators(comic);
        }
        character.setComics(comics);
        characterService.createCharacter(character);
    }

    @Async
    public void synchronizeCreators(Comic comic)
    {
        Long ts = Instant.now().getEpochSecond();
        ApiCreatorResponse apiCreatorResponse = this.marvelGateway.getComicCreators(comic.getId(), publicApiKey, getHash(ts), ts.toString());
        for (ApiCreatorResponse.ApiCreatorResult result : apiCreatorResponse.getApiCharacterData().getResults())
        {
            Collaborator collaborator = result.buildCreator();
            collaboratorService.createCollaborator(collaborator);
        }

    }

    private String getHash(Long timeStamp)
    {
        String prevHash = String.format("%s%s%s", timeStamp, privateApiKey, publicApiKey);
        return DigestUtils.md5DigestAsHex(prevHash.getBytes(StandardCharsets.UTF_8));
    }
}
