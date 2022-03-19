package com.mcalzada.service;

import static org.mockito.Mockito.mock;

import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.CollaboratorRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollaboratorServiceTest
{

    private CollaboratorService collaboratorService;
    private ComicService comicService;
    private CollaboratorRepository collaboratorRepository;

    @BeforeEach
    void setUp()
    {
        comicService = mock(ComicService.class);
        collaboratorRepository = mock(CollaboratorRepository.class);
        collaboratorService = new CollaboratorService(collaboratorRepository, comicService);
    }

    @Test
    void create_collaborators_when_valid_input()
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

        Assertions.assertDoesNotThrow(() -> collaboratorService.createCollaborators(collaborators),
              "Valid collaborator creator should not throw exceptions");
    }
}