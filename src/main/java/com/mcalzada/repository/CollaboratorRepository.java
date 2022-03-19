package com.mcalzada.repository;

import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{

    Optional<Collaborator> findFirstByName(String name);
}
