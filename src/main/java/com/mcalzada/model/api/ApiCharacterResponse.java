package com.mcalzada.model.api;

import com.mcalzada.model.entity.Character;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiCharacterResponse
{

    private Integer code;
    private String status;
    private String copyright;
    private String etag;
    private ApiCharacterData data;

    @Getter
    @Setter
    public static class ApiCharacterData
    {

        private Long offset;
        private Integer limit;
        private Integer total;
        private Integer count;
        private List<ApiCharacterResult> results;
    }

    @Getter
    @Setter
    public static class ApiCharacterResult
    {

        private Long id;
        private String name;
        private String description;
        private String modified;

        public Character buildCharacter()
        {
            return Character.builder().id(this.id).name(this.name).build();
        }

    }
}
