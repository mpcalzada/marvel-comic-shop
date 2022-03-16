package com.mcalzada.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * CharactersResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class CharactersResponse
{

    @JsonProperty("last_sync")
    private Instant lastSync = null;

    @JsonProperty("characters")
    @Valid
    private List<Character> characters = null;

    public CharactersResponse lastSync(Instant lastSync)
    {
        this.lastSync = lastSync;
        return this;
    }

    /**
     * Get lastSync
     *
     * @return lastSync
     **/
    @ApiModelProperty(example = "15/03/2022 23:11:00", value = "")

    @Valid

    public Instant getLastSync()
    {
        return lastSync;
    }

    public void setLastSync(Instant lastSync)
    {
        this.lastSync = lastSync;
    }

    public CharactersResponse characters(List<Character> characters)
    {
        this.characters = characters;
        return this;
    }

    public CharactersResponse addCharactersItem(Character charactersItem)
    {
        if (this.characters == null)
        {
            this.characters = new ArrayList<>();
        }
        this.characters.add(charactersItem);
        return this;
    }

    /**
     * Get characters
     *
     * @return characters
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<Character> getCharacters()
    {
        return characters;
    }

    public void setCharacters(List<Character> characters)
    {
        this.characters = characters;
    }


    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CharactersResponse charactersResponse = (CharactersResponse) o;
        return Objects.equals(this.lastSync, charactersResponse.lastSync) &&
              Objects.equals(this.characters, charactersResponse.characters);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lastSync, characters);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CharactersResponse {\n");

        sb.append("    lastSync: ").append(toIndentedString(lastSync)).append("\n");
        sb.append("    characters: ").append(toIndentedString(characters)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(java.lang.Object o)
    {
        if (o == null)
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

