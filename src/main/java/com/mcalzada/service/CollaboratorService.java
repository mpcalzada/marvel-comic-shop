package com.mcalzada.service;

import com.mcalzada.model.CollaboratorResponse;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.repository.CollaboratorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorService
{

    private final CollaboratorRepository collaboratorRepository;

    public CollaboratorService(CollaboratorRepository collaboratorRepository)
    {
        this.collaboratorRepository = collaboratorRepository;
    }

    public CollaboratorResponse findCollaboratorByHero(String name)
    {
        Optional<Collaborator> collaborators = collaboratorRepository.findFirstByName(name);
        return CollaboratorResponse.builder()
              .lastSync(LocalDateTime.now())
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
