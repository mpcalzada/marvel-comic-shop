package com.mcalzada.model.api;

import com.mcalzada.model.entity.Character;
import com.mcalzada.model.entity.Collaborator;
import com.mcalzada.model.entity.Comic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used as a Data Transfer Object for mapping responses from MarvelAPI into service objects
 *
 * @author Marco Calzada
 * @version 1.0
 * @see com.mcalzada.gateway.MarvelGateway
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiComicsResponse
{

    private Integer code;
    private String status;
    private String copyright;
    private String etag;
    private ApiComicData data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApiComicData
    {

        private Long offset;
        private Integer limit;
        private Integer total;
        private Integer count;
        private List<Results> results;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Results
    {

        private Long id;
        private Long digitalId;
        private String title;
        private String format;
        private String description;
        private String modified;
        private ApiCreators creators;
        private ApiCharacter characters;

        public Comic buildComic()
        {
            Comic comic = Comic.builder()
                  .id(this.id)
                  .name(this.title)
                  .build();
            comic.setCharacters(this.characters.buildCharacter(comic));
            comic.setCollaborators(this.creators.buildCollaborators(comic));

            return comic;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApiCharacter
    {

        private Integer available;
        private String collectionURI;
        private List<Item> items;

        public List<Character> buildCharacter(Comic comic)
        {
            List<Character> characters = new ArrayList<>();
            for (Item item : items)
            {
                Character character = Character.builder()
                      .id(item.getId())
                      .name(item.name)
                      .comics(Collections.singletonList(comic))
                      .build();

                characters.add(character);
            }
            return characters;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApiCreators
    {

        private Integer available;
        private String collectionURI;
        private List<Item> items;

        public List<Collaborator> buildCollaborators(Comic comic)
        {
            List<Collaborator> collaborators = new ArrayList<>();
            for (Item item : items)
            {
                Collaborator collaborator = Collaborator.builder()
                      .id(item.getId())
                      .name(item.name)
                      .role(item.role)
                      .comics(Collections.singletonList(comic))
                      .build();

                collaborators.add(collaborator);
            }
            return collaborators;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
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
