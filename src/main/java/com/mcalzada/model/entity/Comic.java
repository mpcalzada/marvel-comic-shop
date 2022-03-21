package com.mcalzada.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Builder;

/**
 * This class is a mapped entity for comic repository operations
 *
 * @author Marco Calzada
 * @version 1.0
 * @see com.mcalzada.repository.ComicRepository
 */
@Entity
@Builder
public class Comic
{

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
          name = "character_comics",
          joinColumns = @JoinColumn(name = "comic_id"),
          inverseJoinColumns = @JoinColumn(name = "character_id"))
    List<Character> characters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
          name = "collaborator_comics",
          joinColumns = @JoinColumn(name = "comic_id"),
          inverseJoinColumns = @JoinColumn(name = "collaborator_id"))
    List<Collaborator> collaborators;

    public Comic()
    {

    }

    public Comic(Long id, String name, List<Character> characters, List<Collaborator> collaborators)
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

    public List<Character> getCharacters()
    {
        return characters;
    }

    public void setCharacters(List<Character> characters)
    {
        this.characters = characters;
    }

    public List<Collaborator> getCollaborators()
    {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators)
    {
        this.collaborators = collaborators;
    }

    @Override
    public String toString()
    {
        return "{\"name\"=\"" + name + "\"}";
    }
}
