package com.mcalzada.repository;

import com.mcalzada.model.entity.Character;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer>
{

    Optional<Character> findFirstByName(String name);
}
