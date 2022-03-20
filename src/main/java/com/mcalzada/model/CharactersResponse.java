package com.mcalzada.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * CharactersResponse
 */
@Validated
@Data
@Builder
public class CharactersResponse
{

    @JsonProperty("last_sync")
    private LocalDateTime lastSync = null;

    @JsonProperty("characters")
    @Valid
    private List<CharacterResponse> characters = null;

    @Data
    @Builder
    public static class CharacterResponse
    {

        private String name;
        private List<String> comics;

        public void addComic(String comicName)
        {
            this.comics.add(comicName);
        }

    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}

