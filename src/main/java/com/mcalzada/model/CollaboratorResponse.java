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
 * CollaboratorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

public class CollaboratorResponse
{

    @JsonProperty("last_sync")
    private Instant lastSync = null;

    @JsonProperty("editors")
    @Valid
    private List<String> editors = null;

    @JsonProperty("writers")
    @Valid
    private List<String> writers = null;

    @JsonProperty("colorist")
    @Valid
    private List<String> colorist = null;

    public CollaboratorResponse lastSync(Instant lastSync)
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

    public CollaboratorResponse editors(List<String> editors)
    {
        this.editors = editors;
        return this;
    }

    public CollaboratorResponse addEditorsItem(String editorsItem)
    {
        if (this.editors == null)
        {
            this.editors = new ArrayList<>();
        }
        this.editors.add(editorsItem);
        return this;
    }

    /**
     * Get editors
     *
     * @return editors
     **/
    @ApiModelProperty(example = "[\"Wilson Moss\",\"Andy Smidth\"]", value = "")

    public List<String> getEditors()
    {
        return editors;
    }

    public void setEditors(List<String> editors)
    {
        this.editors = editors;
    }

    public CollaboratorResponse writers(List<String> writers)
    {
        this.writers = writers;
        return this;
    }

    public CollaboratorResponse addWritersItem(String writersItem)
    {
        if (this.writers == null)
        {
            this.writers = new ArrayList<>();
        }
        this.writers.add(writersItem);
        return this;
    }

    /**
     * Get writers
     *
     * @return writers
     **/
    @ApiModelProperty(example = "[\"Ed Brubaker\",\"Ryan North\"]", value = "")

    public List<String> getWriters()
    {
        return writers;
    }

    public void setWriters(List<String> writers)
    {
        this.writers = writers;
    }

    public CollaboratorResponse colorist(List<String> colorist)
    {
        this.colorist = colorist;
        return this;
    }

    public CollaboratorResponse addColoristItem(String coloristItem)
    {
        if (this.colorist == null)
        {
            this.colorist = new ArrayList<>();
        }
        this.colorist.add(coloristItem);
        return this;
    }

    /**
     * Get colorist
     *
     * @return colorist
     **/
    @ApiModelProperty(example = "[\"Rico Renzi\"]", value = "")

    public List<String> getColorist()
    {
        return colorist;
    }

    public void setColorist(List<String> colorist)
    {
        this.colorist = colorist;
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
        CollaboratorResponse collaboratorResponse = (CollaboratorResponse) o;
        return Objects.equals(this.lastSync, collaboratorResponse.lastSync) &&
              Objects.equals(this.editors, collaboratorResponse.editors) &&
              Objects.equals(this.writers, collaboratorResponse.writers) &&
              Objects.equals(this.colorist, collaboratorResponse.colorist);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lastSync, editors, writers, colorist);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollaboratorResponse {\n");

        sb.append("    lastSync: ").append(toIndentedString(lastSync)).append("\n");
        sb.append("    editors: ").append(toIndentedString(editors)).append("\n");
        sb.append("    writers: ").append(toIndentedString(writers)).append("\n");
        sb.append("    colorist: ").append(toIndentedString(colorist)).append("\n");
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

