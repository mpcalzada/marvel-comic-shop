package com.mcalzada.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String name;
    private Role role;

    public Collaborator()
    {

    }

    public Collaborator(Long id, String name, Role role)
    {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
