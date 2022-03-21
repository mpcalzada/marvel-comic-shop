package com.mcalzada.repository;

import com.mcalzada.model.entity.Comic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ComicRepository is a JEE Bean built over the service layer for the application. Performs operation over SQL databases using {@link JpaRepository} based
 * on configuration in application.properties
 *
 * @author Marco Calzada
 * @version 1.0
 */
public interface ComicRepository extends JpaRepository<Comic, Long>
{

    /**
     * Find all the comics for the provided character name
     *
     * @param characterName character name as expected in {@link com.mcalzada.model.entity.Character} entity
     * @return the list of comics that the character participated in
     */
    List<Comic> findComicsByCharactersName(String characterName);
}
