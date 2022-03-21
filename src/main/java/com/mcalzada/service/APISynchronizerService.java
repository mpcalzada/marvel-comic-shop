package com.mcalzada.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mcalzada.gateway.MarvelGateway;
import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiCharacterResponse.ApiCharacterResult;
import com.mcalzada.model.api.ApiComicsResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Comic;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * APISynchronizerService is a JEE Bean built over the service layer for the application. Performs domain operations within the controller, gateway and
 * repositories. For more information see {@link "https://developer.marvel.com"}
 *
 * @author Marco Calzada
 * @version 1.0
 */
@Service
@Log4j2
@EnableScheduling
public class APISynchronizerService
{

    private static final ExecutorService comicSyncExecutorService = Executors.newFixedThreadPool(10,
          new ThreadFactoryBuilder().setNameFormat("COMIC-SYNC-%d").build());
    private final MarvelGateway marvelGateway;
    private final ComicService comicService;
    private final CharacterService characterService;
    private final CollaboratorService collaboratorService;

    @Value("#{'${marvel.characters.sync}'.split(',')}")
    private List<String> requiredHeros;
    @Value("${marvel.api.public-api-key}")
    private String publicApiKey;
    @Value("${marvel.api.private-api-key}")
    private String privateApiKey;

    /**
     * @param marvelGateway       Provided gatewoy for establishing communication with Marvel API
     * @param comicService        {@link ComicService} implementation for performing comic operations
     * @param characterService    {@link CharacterService} implementation for performing characters operations
     * @param collaboratorService {@link CollaboratorService} implementation for performing collaborators operations
     */
    @Autowired
    public APISynchronizerService(MarvelGateway marvelGateway, ComicService comicService, CharacterService characterService,
          CollaboratorService collaboratorService)
    {
        this.marvelGateway = marvelGateway;
        this.comicService = comicService;
        this.characterService = characterService;
        this.collaboratorService = collaboratorService;
    }

    /**
     * This event listener is triggered on SpringBoot application startup. After all beans have been loaded
     *
     * @param event Triggered event
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        this.synchronizeExpiredCharacters();
    }

    /**
     * This method is invoked by SpringBoot scheduler {@link Scheduled} based on a cron expression configured in application.properties file
     */
    @Scheduled(cron = "${synchronization.crontab}")
    public void synchronizeExpiredCharacters()
    {
        for (String hero : requiredHeros.stream().filter(characterService::characterUpToDate).collect(Collectors.toList()))
        {
            synchronizeHero(hero);
        }
    }

    /**
     * This method synchronizes the provided hero from Marvel API into the configured datasource
     *
     * @param heroName requested to be synced
     */
    public void synchronizeHero(String heroName)
    {
        log.debug("Starting sync for hero {} ", heroName);
        try
        {
            Long ts = Instant.now().getEpochSecond();
            ApiCharacterResponse apiCharacterResponse = this.marvelGateway.getCharacterByName(heroName, publicApiKey, getHash(ts), ts.toString());
            for (ApiCharacterResult result : apiCharacterResponse.getData().getResults())
            {
                Character character = result.buildCharacter();
                this.synchronizeComics(character);
            }
        }
        catch (Exception e)
        {
            log.fatal("Unable to sync data from Marvel API", e);
        }
    }

    /**
     * Based on the character information, the method looks for the comics for the provided character and synchronize Collaborator/Comic/Character
     * information from Marvel API into the repositories.
     *
     * @param character requested character
     */
    public void synchronizeComics(Character character)
    {
        long totalCount = 0;
        long fetched = 0;
        do
        {
            Long ts = Instant.now().getEpochSecond();
            log.info("Fetching data from marvelGateway for character {} with offset {} and current limit {}", character, fetched, totalCount);
            ApiComicsResponse apiComicsResponse = this.marvelGateway.getComicsByCharacter(character.getId(), fetched, publicApiKey, getHash(ts),
                  ts.toString());
            totalCount = apiComicsResponse.getData().getTotal();
            fetched += apiComicsResponse.getData().getCount();

            comicSyncExecutorService.submit(() -> processComicResults(apiComicsResponse));

            log.info("Data written into database total {} from {}", fetched, character);
        }
        while (fetched < totalCount);
    }

    /**
     * This method was designed to be invoked by a {@link ExecutorService} and perform operations into the database
     *
     * @param apiComicsResponse Marvel API pre-processed response
     */
    private void processComicResults(ApiComicsResponse apiComicsResponse)
    {
        List<Comic> comics = new ArrayList<>();
        for (ApiComicsResponse.Results result : apiComicsResponse.getData().getResults())
        {
            comics.add(result.buildComic());
            log.debug("Building characters and collaborators from {}", result);
        }
        comicService.createComics(comics);
    }

    /**
     * Hashing algorithm for generating api-keys based on marvel docs. {@linkplain "https://developer.marvel.com/documentation/authorization"}
     *
     * @param timeStamp current timestamp
     * @return generated hash.
     */
    private String getHash(Long timeStamp)
    {
        String prevHash = String.format("%s%s%s", timeStamp, privateApiKey, publicApiKey);
        return DigestUtils.md5DigestAsHex(prevHash.getBytes(StandardCharsets.UTF_8));
    }
}
