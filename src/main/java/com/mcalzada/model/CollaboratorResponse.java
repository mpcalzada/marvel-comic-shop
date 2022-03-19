package com.mcalzada.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

/**
 * CollaboratorResponse
 */
@Validated
@Builder
public class CollaboratorResponse
{

    @JsonProperty("last_sync")
    private LocalDateTime lastSync = null;

    @JsonProperty("editors")
    @Valid
    private List<String> editors = null;

    @JsonProperty("writers")
    @Valid
    private List<String> writers = null;

    @JsonProperty("colorist")
    @Valid
    private List<String> colorist = null;

}

