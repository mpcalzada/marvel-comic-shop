package com.mcalzada.repository;

import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{

    List<Collaborator> findDistinctNameByComicsIn(List<Comic> comics);

}
