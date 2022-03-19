package com.mcalzada.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import lombok.Builder;

@Entity
@Builder
public class Collaborator
{

    @Id
    private long id;
    @Column
    private String name;
    @Column
    private String role;

    @Valid
    @JsonProperty("comics")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
          name = "collaborator_comics",
          joinColumns = @JoinColumn(name = "collaborator_id"),
          inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private List<Comic> comics = null;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Collaborator()
    {

    }

    public Collaborator(Long id, String name, String role, List<Comic> comics, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
        this.role = role;
        this.comics = comics;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setComics(List<Comic> comics)
    {
        this.comics = comics;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
}
