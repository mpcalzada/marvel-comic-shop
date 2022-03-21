package com.mcalzada.repository;

import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CollaboratorRepository is a JEE Bean built over the service layer for the application. Performs operation over SQL databases using {@link JpaRepository}
 * based on configuration in application.properties
 *
 * @author Marco Calzada
 * @version 1.0
 */
@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{

    /**
     * Finds all the collaborators that participated in the provided list of comics
     *
     * @param comics searching comics
     * @return List of collaborators that participated in the provided comics. Empty list if no coincidences
     */
    List<Collaborator> findDistinctNameByComicsIn(List<Comic> comics);

}
