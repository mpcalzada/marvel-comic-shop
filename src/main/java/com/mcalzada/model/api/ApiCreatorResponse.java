package com.mcalzada.model.api;

import com.mcalzada.model.entity.Collaborator;
import java.util.List;
import javax.persistence.Id;
import lombok.Getter;
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
public class ApiCreatorResponse
{

    private Integer code;
    private String status;
    private String copyright;
    private String etag;
    private ApiCharacterData apiCharacterData;

    @Getter
    @Setter
    public static class ApiCharacterData
    {

        private Long offset;
        private Integer limit;
        private Integer total;
        private Integer count;
        private List<ApiCreatorResult> results;
    }

    @Getter
    @Setter
    public static class ApiCreatorResult
    {

        @Id
        private Long id;
        private String name;
        private String description;
        private String modified;

        public Collaborator buildCreator()
        {
            return Collaborator.builder().id(this.id).name(this.name).build();
        }
    }
}
