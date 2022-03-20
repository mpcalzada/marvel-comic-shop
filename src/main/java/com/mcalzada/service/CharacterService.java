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

@Service
public class CharacterService
{

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository)
    {
        this.characterRepository = characterRepository;
    }

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

    public void createCharacters(List<Character> comicCharacters)
    {
        characterRepository.saveAll(comicCharacters);
    }

    public boolean characterUpToDate(String heroName)
    {
        Optional<Character> foundedHero = characterRepository.findFirstByName(heroName);
        return !foundedHero.isPresent() || foundedHero.get().isExpiredEntity();
    }
}
