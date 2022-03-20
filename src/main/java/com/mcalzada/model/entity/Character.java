package com.mcalzada.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;

    @JsonProperty("character")
    @Column(name = "name")
    private String name = null;

    @Valid
    @JsonProperty("comics")
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Comic> comics = null;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Character()
    {
        comics = new ArrayList<>();
        this.updatedAt = LocalDateTime.now();
    }

    public Character(Long id, String name, List<Comic> comics, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
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

    public boolean isExpiredEntity()
    {
        return LocalDateTime.now().minusHours(23).isAfter(updatedAt);
    }

    public void addComic(Comic comic)
    {
        this.comics.add(comic);
    }

    @Override
    public String toString()
    {
        return "{\"name\"=\"" + name + "\" ,\"comics\"=[" + comics + "]}";
    }
}

