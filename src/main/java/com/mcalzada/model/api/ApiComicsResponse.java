package com.mcalzada.model.api;

import com.mcalzada.model.entity.Comic;
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

        public Comic buildComic()
        {
            return Comic.builder().id(this.id).name(this.title).build();
        }
    }

    @Getter
    @Setter
    public static class Creators
    {

        private Integer available;
        private String collectionURI;
        private List<Items> items;
    }

    @Getter
    @Setter
    public static class Items
    {

        private String resourceURI;
        private String name;
        private String role;
    }
}
