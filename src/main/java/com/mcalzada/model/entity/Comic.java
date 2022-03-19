package com.mcalzada.model.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Builder;

@Entity
@Builder
public class Comic
{

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "comics")
    Set<Character> characters;

    @ManyToMany(mappedBy = "comics")
    Set<Collaborator> collaborators;

    public Comic()
    {

    }

    public Comic(Long id, String name, Set<Character> characters, Set<Collaborator> collaborators)
    {
        this.id = id;
        this.name = name;
        this.characters = characters;
        this.collaborators = collaborators;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
