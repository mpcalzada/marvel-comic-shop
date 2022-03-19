package com.mcalzada.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mcalzada.controllers.exception.ApiException;
import com.mcalzada.model.CharactersResponse;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.CharacterRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CharacterServiceTest
{

    private CharacterService characterService;
    private CharacterRepository characterRepository;

    @BeforeEach
    void setUp()
    {
        characterRepository = Mockito.mock(CharacterRepository.class);
        characterService = new CharacterService(characterRepository);
    }

    @Test
    void return_character_information_when_name_exists() throws ApiException
    {
        List<Comic> comics1 = new ArrayList<>();
        List<Comic> comics2 = new ArrayList<>();
        List<Collaborator> collaborators = new ArrayList<>();
        List<Character> characters = new ArrayList<>();

        comics1.add(new Comic(98402L, "Captain America/Iron Man (2021) #4", Collections.emptyList(), collaborators));
        comics1.add(new Comic(98401L, "Captain America/Iron Man (2021) #3", Collections.emptyList(), collaborators));

        comics2.add(new Comic(89681L, "Iron Man (2020) #15", characters, null));
        comics2.add(new Comic(89679L, "Iron Man (2020) #13", characters, null));

        List<Comic> fullComics = new ArrayList<>(comics1);
        fullComics.addAll(comics2);

        collaborators.add(new Collaborator(5251L, "Vc Joe Caramagna", "letterer", comics1, LocalDateTime.now(), LocalDateTime.now()));
        collaborators.add(new Collaborator(12861L, "Derek Landy", "writer", comics1, LocalDateTime.now(), LocalDateTime.now()));

        characters.add(new Character(1009368L, "Iron Man", fullComics, LocalDateTime.now(), LocalDateTime.now()));
        characters.add(new Character(1010351L, "Hellcat (Patsy Walker)", comics2, LocalDateTime.now(), LocalDateTime.now()));
        characters.add(new Character(1011312L, "Korvac", comics2, LocalDateTime.now(), LocalDateTime.now()));

        Character character = new Character(1L, "Iron Man", fullComics, LocalDateTime.now(), LocalDateTime.now());

        when(characterRepository.findFirstByName(any())).thenReturn(Optional.of(character));

        CharactersResponse ironmanApiResponse = characterService.findCharacterByName("Iron Man");

        assertEquals(3, ironmanApiResponse.getCharacters().size(), "3 heroes should've been returned");
        assertEquals(2, ironmanApiResponse.getCharacters().get(2).getComics().size(), "Hero #2 should've had 2 comics");
    }

    @Test
    void return_api_error_when_hero_not_found()
    {
        when(characterRepository.findFirstByName(any())).thenReturn(Optional.empty());
        assertThrows(ApiException.class, () -> characterService.findCharacterByName("Superman"));
    }

    @Test
    void return_api_error_when_invalid_data_provided()
    {
        when(characterRepository.findFirstByName(null)).thenThrow(IllegalArgumentException.class);
        assertThrows(RuntimeException.class, () -> characterService.findCharacterByName(null));
    }

    @Test
    void create_new_character_when_requested()
    {
        List<Character> characters = Arrays.asList(
              new Character(1L, "Iron Man", Collections.singletonList(new Comic()), LocalDateTime.now(), LocalDateTime.now()),
              new Character(1L, "Captain America", Collections.singletonList(new Comic()), LocalDateTime.now(), LocalDateTime.now()),
              new Character(1L, "Wolverine", Collections.singletonList(new Comic()), LocalDateTime.now(), LocalDateTime.now())
        );

        characterService.createCharacters(characters);
    }
}