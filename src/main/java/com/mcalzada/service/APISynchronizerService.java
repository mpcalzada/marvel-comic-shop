package com.mcalzada.service;

import com.mcalzada.gateway.MarvelGateway;
import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiCharacterResponse.ApiCharacterResult;
import com.mcalzada.model.api.ApiComicsResponse;
import com.mcalzada.model.api.ApiCreatorResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Log4j2
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

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        this.synchronizeExpiredCharacters();
    }

    @Scheduled(cron = "${synchronization.crontab}")
    public void synchronizeExpiredCharacters()
    {
        for (String hero : requiredHeros.stream().filter(characterService::characterUpToDate).collect(Collectors.toList()))
        {
            synchronizeHero(hero);
        }
    }

    @Async
    public void synchronizeHero(String heroName)
    {
        log.debug("Starting sync for hero {} ", heroName);
        Long ts = Instant.now().getEpochSecond();
        ApiCharacterResponse apiCharacterResponse = this.marvelGateway.getCharacterByName(heroName, publicApiKey, getHash(ts), ts.toString());
        for (ApiCharacterResult result : apiCharacterResponse.getData().getResults())
        {
            Character character = result.buildCharacter();
            this.synchronizeComics(character);
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

            List<Character> comicCharacters = result.getCharacters().buildCharacter(comics);
            List<Collaborator> comicCollaborators = result.getCreators().buildCollaborators(comics);

            characterService.createCharacters(comicCharacters);
            collaboratorService.createCollaborators(comicCollaborators);
        }
    }

    private String getHash(Long timeStamp)
    {
        String prevHash = String.format("%s%s%s", timeStamp, privateApiKey, publicApiKey);
        return DigestUtils.md5DigestAsHex(prevHash.getBytes(StandardCharsets.UTF_8));
    }
}
