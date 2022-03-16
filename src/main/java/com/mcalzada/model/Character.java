package com.mcalzada.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * Character
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class Character
{

    @JsonProperty("character")
    private String character = null;

    @JsonProperty("comics")
    @Valid
    private List<String> comics = null;

    public Character character(String character)
    {
        this.character = character;
        return this;
    }

    /**
     * Get character
     *
     * @return character
     **/
    @ApiModelProperty(example = "Squirrel Girl", value = "")

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String character)
    {
        this.character = character;
    }

    public Character comics(List<String> comics)
    {
        this.comics = comics;
        return this;
    }

    public Character addComicsItem(String comicsItem)
    {
        if (this.comics == null)
        {
            this.comics = new ArrayList<>();
        }
        this.comics.add(comicsItem);
        return this;
    }

    /**
     * Get comics
     *
     * @return comics
     **/
    @ApiModelProperty(example = "[\"The Unbeateable Squirrel Girl (2015) #38\",\"The Unbeateable Squirrel Girl(2015) #39\"]", value = "")

    public List<String> getComics()
    {
        return comics;
    }

    public void setComics(List<String> comics)
    {
        this.comics = comics;
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
        Character character = (Character) o;
        return Objects.equals(this.character, character.character) &&
              Objects.equals(this.comics, character.comics);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(character, comics);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Character {\n");

        sb.append("    character: ").append(toIndentedString(character)).append("\n");
        sb.append("    comics: ").append(toIndentedString(comics)).append("\n");
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

