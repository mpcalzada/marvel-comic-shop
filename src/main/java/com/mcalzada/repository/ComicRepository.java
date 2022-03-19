package com.mcalzada.repository;

import com.mcalzada.model.entity.Comic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long>
{

    List<Comic> findComicsByCharactersName(String characterName);
}
