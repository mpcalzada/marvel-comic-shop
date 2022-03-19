package com.mcalzada.service;

import com.mcalzada.model.entity.Character;
import com.mcalzada.repository.CharacterRepository;
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

    public Optional<Character> findCharacterByName(String name)
    {
        return characterRepository.findFirstByName(name);
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
