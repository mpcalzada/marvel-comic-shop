package com.mcalzada.model.api;

import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiComicsResponse
{

    private Integer code;
    private String status;
    private String copyright;
    private String etag;
    private Data data;

    @Getter
    @Setter
    public static class Data
    {

        private Long offset;
        private Integer limit;
        private Integer total;
        private Integer count;
        private List<Results> results;
    }

    @Getter
    @Setter
    public static class Results
    {

        private Long id;
        private Long digitalId;
        private String title;
        private String format;
        private String description;
        private String modified;
        private Creators creators;
        private ApiCharacter characters;

        public Comic buildComic()
        {
            return Comic.builder().id(this.id).name(this.title).build();
        }
    }

    @Getter
    @Setter
    public static class ApiCharacter
    {

        private Integer available;
        private String collectionURI;
        private List<Item> items;

        public List<Character> buildCharacter(List<Comic> comics)
        {
            List<Character> characters = new ArrayList<>();
            for (Item item : items)
            {
                characters.add(Character.builder()
                      .id(item.getId())
                      .name(item.name)
                      .comics(comics)
                      .build());
            }
            return characters;
        }
    }

    @Getter
    @Setter
    public static class Creators
    {

        private Integer available;
        private String collectionURI;
        private List<Item> items;

        public List<Collaborator> buildCollaborators(List<Comic> comics)
        {
            List<Collaborator> collaborators = new ArrayList<>();
            for (Item item : items)
            {
                collaborators.add(Collaborator.builder()
                      .id(item.getId())
                      .name(item.name)
                      .role(item.role)
                      .comics(comics)
                      .build());
            }
            return collaborators;
        }
    }

    @Getter
    @Setter
    public static class Item
    {

        private String resourceURI;
        private String name;
        private String role;

        public Long getId()
        {
            return Long.parseLong(resourceURI.replaceAll("^[\\w\\W]+/", ""));
        }
    }
}
