package com.mcalzada.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.mcalzada.model.entity.Character;
import java.time.Instant;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * CharactersResponse
 */
@Validated

public class CharactersResponse
{

    @JsonProperty("last_sync")
    private Instant lastSync = null;

    @JsonProperty("characters")
    @Valid
    private List<Character> characters = null;

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}

