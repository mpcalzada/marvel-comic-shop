package com.mcalzada.service;

import com.mcalzada.controllers.exception.ApiException;
import com.mcalzada.model.CharactersResponse;
import com.mcalzada.model.CharactersResponse.CharacterResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.CharacterRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CharacterService is a JEE Bean built over the service layer for the application. Performs domain operations within the controller, gateway and
 * repositories
 *
 * @author Marco Calzada
 * @version 1.0
 */
@Service
public class CharacterService
{

    private final CharacterRepository characterRepository;

    /**
     * Service constructor auto-invoked by springboot context
     *
     * @param characterRepository provided repository to perform data-gather operations
     */
    @Autowired
    public CharacterService(CharacterRepository characterRepository)
    {
        this.characterRepository = characterRepository;
    }

    /**
     * This method looks inside the {@link CharacterRepository} looking for the list of comics and links each comic with the other characters that
     * participated in it
     *
     * @param name of the requested hero
     * @return {@link CharacterResponse} as needed by the controller
     * @throws ApiException if provided data is wrong or couldn´t find anything related to provided name
     */
    public CharactersResponse findCharacterByName(String name) throws ApiException
    {
        Optional<Character> characterDetail = characterRepository.findFirstByName(name);
        if (!characterDetail.isPresent())
        {
            throw new ApiException(404, "Hero couldn't be found in the system");
        }

        HashMap<String, CharacterResponse> secondaryCharacters = new HashMap<>();
        for (Comic comic : characterDetail.get().getComics())
        {
            for (Character character : comic.getCharacters())
            {
                if (!secondaryCharacters.containsKey(character.getName()))
                {
                    List<String> comics = new ArrayList<>();
                    comics.add(comic.getName());
                    secondaryCharacters.put(character.getName(), CharacterResponse.builder()
                          .name(character.getName())
                          .comics(comics)
                          .build());
                }
                else
                {
                    secondaryCharacters.get(character.getName()).addComic(comic.getName());
                }
            }
        }

        return CharactersResponse.builder()
              .lastSync(characterDetail.get().getUpdatedAt())
              .characters(new ArrayList<>(secondaryCharacters.values()))
              .build();
    }

    /**
     * Creates the provided characters in the configured repository
     *
     * @param comicCharacters List of characters requested to create
     */
    public void createCharacters(List<Character> comicCharacters)
    {
        characterRepository.saveAll(comicCharacters);
    }

    /**
     * Validates if requested character name is still up-to-date
     *
     * @param heroName Requested character name
     * @return true if character is up-to-date. false if it has already expired or not found
     */
    public boolean characterUpToDate(String heroName)
    {
        Optional<Character> foundedHero = characterRepository.findFirstByName(heroName);
        return !foundedHero.isPresent() || foundedHero.get().isExpiredEntity();
    }
}
