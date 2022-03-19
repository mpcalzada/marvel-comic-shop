package com.mcalzada.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
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

    public enum Role
    {
        EDITOR, WRITER, COLORIST
    }

    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Role role;

    @Valid
    @JsonProperty("comics")
    @ManyToMany
    @JoinTable(
          name = "collaborator_comics",
          joinColumns = @JoinColumn(name = "collaborator_id"),
          inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private List<Comic> comics = null;

    @Version
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Collaborator()
    {

    }

    public Collaborator(Long id, String name, Role role, List<Comic> comics, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        this.id = id;
        this.name = name;
        this.role = role;
        this.comics = comics;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
