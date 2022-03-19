package com.mcalzada.service;

import com.google.gson.internal.LinkedTreeMap;
import com.mcalzada.model.CollaboratorResponse;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import com.mcalzada.repository.CollaboratorRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorService
{

    private final CollaboratorRepository collaboratorRepository;
    private final ComicService comicService;

    @Autowired
    public CollaboratorService(CollaboratorRepository collaboratorRepository, ComicService comicService)
    {
        this.collaboratorRepository = collaboratorRepository;
        this.comicService = comicService;
    }

    public CollaboratorResponse findCollaboratorByHero(String name)
    {
        List<Comic> comics = comicService.searchComicsByCharacterName(name);
        List<Collaborator> collaborators = collaboratorRepository.findDistinctNameByComicsIn(comics);

        LinkedTreeMap<String, List<String>> collaboratorsPerRoles = new LinkedTreeMap<>();

        for (Collaborator collaborator : collaborators)
        {
            List<String> groupedCollab = collaboratorsPerRoles.getOrDefault(collaborator.getRole(), new ArrayList<>());
            groupedCollab.add(collaborator.getName());
            collaboratorsPerRoles.put(collaborator.getRole(), groupedCollab);
        }

        return CollaboratorResponse.builder()
              .lastSync(collaborators.stream().findAny().orElse(Collaborator.builder().updatedAt(LocalDateTime.now()).build()).getUpdatedAt())
              .colorist(collaboratorsPerRoles.get("colorist"))
              .editors(collaboratorsPerRoles.get("editor"))
              .writers(collaboratorsPerRoles.get("writer"))
              .build();
    }

    public void createCollaborator(Collaborator collaborator)
    {
        collaboratorRepository.save(collaborator);
    }

    public void createCollaborators(List<Collaborator> collaborator)
    {
        collaboratorRepository.saveAll(collaborator);
    }
}
