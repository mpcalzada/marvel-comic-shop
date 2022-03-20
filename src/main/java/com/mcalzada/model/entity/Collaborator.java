package com.mcalzada.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @ManyToMany(mappedBy = "collaborators", cascade = CascadeType.ALL)
    private List<Comic> comics = null;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Collaborator()
    {
        comics = new ArrayList<>();
        this.updatedAt = LocalDateTime.now();
    }

    public Collaborator(Long id, String name, String role, List<Comic> comics, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
        this.role = role;
        this.comics = comics;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = createdAt;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public List<Comic> getComics()
    {
        return comics;
    }

    public void setComics(List<Comic> comics)
    {
        this.comics = comics;
    }

    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
}
