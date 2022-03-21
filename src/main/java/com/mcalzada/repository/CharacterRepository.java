package com.mcalzada.repository;

import com.mcalzada.model.entity.Character;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CharacterRepository is a JEE Bean built over the service layer for the application. Performs operation over SQL databases using {@link JpaRepository}
 * based on configuration in application.properties
 *
 * @author Marco Calzada
 * @version 1.0
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>
{

    /**
     * Search for a Character filtered for the character name
     *
     * @param name character name
     * @return Optional character or Optional.empty if not found
     */
    Optional<Character> findFirstByName(String name);
}
