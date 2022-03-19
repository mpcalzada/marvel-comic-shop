package com.mcalzada.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

/**
 * Character
 */
@Validated
@Builder
@Entity
public class Character
{

    @Id
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @JsonProperty("character")
    @Column(name = "name")
    private String name = null;

    @Valid
    @JsonProperty("comics")
    @OneToMany
    @JoinColumn(referencedColumnName = "id", name = "character_id")
    private List<Comic> comics = null;

    @Version
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Character()
    {
    }

    public Character(Long id, String name, List<Comic> comics, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
        this.comics = comics;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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

    public List<Comic> getComics()
    {
        return comics;
    }

    public void setComics(List<Comic> comics)
    {
        this.comics = comics;
    }

    public boolean expired()
    {
        return LocalDateTime.now().minusHours(23).isAfter(updatedAt);
    }
}

