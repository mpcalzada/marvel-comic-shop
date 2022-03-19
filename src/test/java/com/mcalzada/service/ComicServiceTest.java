package com.mcalzada.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.ComicRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComicServiceTest
{

    private ComicService comicService;
    private ComicRepository comicRepository;

    @BeforeEach
    void setUp()
    {
        comicRepository = mock(ComicRepository.class);
        comicService = new ComicService(comicRepository);
    }

    @Test
    void return_comic_when_exists()
    {
        List<Comic> comics = new ArrayList<>();
        List<Collaborator> collaborators = new ArrayList<>();
        List<Character> characters = new ArrayList<>();

        comics.add(new Comic(98402L, "Captain America/Iron Man (2021) #4", characters, collaborators));
        comics.add(new Comic(98401L, "Captain America/Iron Man (2021) #3", characters, collaborators));
        comics.add(new Comic(89681L, "Iron Man (2020) #15", characters, collaborators));
        comics.add(new Comic(89679L, "Iron Man (2020) #13", characters, collaborators));

        collaborators.add(new Collaborator(5251L, "Vc Joe Caramagna", "letterer", comics, LocalDateTime.now(), LocalDateTime.now()));
        collaborators.add(new Collaborator(12861L, "Derek Landy", "writer", comics, LocalDateTime.now(), LocalDateTime.now()));

        characters.add(new Character(1009368L, "Iron Man", comics, LocalDateTime.now(), LocalDateTime.now()));
        characters.add(new Character(1010351L, "Hellcat (Patsy Walker)", comics, LocalDateTime.now(), LocalDateTime.now()));
        characters.add(new Character(1011312L, "Korvac", comics, LocalDateTime.now(), LocalDateTime.now()));

        when(comicRepository.findComicsByCharactersName(any())).thenReturn(comics);

        List<Comic> comicsResult = comicService.searchComicsByCharacterName("Iron man");

        Assertions.assertEquals(comics, comicsResult, "Comics gathered from database should be returned");
    }

    @Test
    void return_empty_list_when_hero_not_found()
    {
        when(comicRepository.findComicsByCharactersName(any())).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), comicService.searchComicsByCharacterName("Superman"), "Comic list should be empty when comics not found");
    }

    @Test
    void return_api_error_when_invalid_data_provided()
    {
        when(comicRepository.findComicsByCharactersName(null)).thenThrow(IllegalArgumentException.class);
        assertThrows(RuntimeException.class, () -> comicService.searchComicsByCharacterName(null), "All exceptions thrown by database should be thrown");
    }


}