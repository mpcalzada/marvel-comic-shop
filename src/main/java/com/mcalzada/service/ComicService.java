package com.mcalzada.service;

import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.ComicRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ComicService
{

    private final ComicRepository comicRepository;

    @Autowired
    public ComicService(ComicRepository comicRepository)
    {
        this.comicRepository = comicRepository;
    }

    public List<Comic> searchComicsByCharacterName(String characterName)
    {
        return comicRepository.findComicsByCharactersName(characterName);
    }
}
