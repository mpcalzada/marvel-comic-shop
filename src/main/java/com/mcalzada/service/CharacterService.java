package com.mcalzada.service;

import com.mcalzada.model.CharactersResponse;
import com.mcalzada.model.CharactersResponse.CharacterResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.CharacterRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    public CharactersResponse findCharacterByName(String name)
    {
        Optional<Character> characterDetail = characterRepository.findFirstByName(name);
        if (!characterDetail.isPresent())
        {
            // FIXME: 19/03/2022 This must be an API exception
            throw new IllegalArgumentException("Not found");
        }

        HashSet<CharacterResponse> secondaryCharacters = new HashSet<>();
        for (Comic comic : characterDetail.get().getComics())
        {
            for (Character character : comic.getCharacters())
            {
                CharacterResponse characterResponse = CharacterResponse.builder()
                      .name(character.getName())
                      .comics(character.getComics().stream().map(Comic::getName).collect(Collectors.toList()))
                      .build();
                secondaryCharacters.add(characterResponse);
            }
        }

        // FIXME: 19/03/2022 Fix or remove
        //HashMap<Object, Object> charactersPerComic = characterDetail.get().getComics().stream().map(Comic::getCharacters)
        //      .collect(HashMap::new, (m, ch) -> m.put(ch.), Map::putAll);
        //HashMap<Object, Object> charactersPerComic = characterDetail.get().getComics().stream()
        //      .collect(HashMap::new, (m, c) -> m.put(c.getName(), c.getCharacters()), Map::putAll);

        return CharactersResponse.builder()
              .lastSync(characterDetail.get().getUpdatedAt())
              .characters(new ArrayList<>(secondaryCharacters))
              .build();
    }

    public void createCharacter(Character character)
    {
        characterRepository.save(character);
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
