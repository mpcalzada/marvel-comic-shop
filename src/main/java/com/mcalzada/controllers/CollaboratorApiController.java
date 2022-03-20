package com.mcalzada.controllers;

import com.mcalzada.controllers.exception.ApiException;
import com.mcalzada.model.CollaboratorResponse;
import com.mcalzada.service.CollaboratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.concurrent.CompletableFuture;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@javax.annotation.Generated(value = "io.swagger.responseCodegen.languages.SpringresponseCodegen", date = "2022-03-15T06:36:53.230Z")

@Controller
@Validated
@Log4j2
@RequestMapping(value = "/marvel")
public class CollaboratorApiController
{

    private final CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorApiController(CollaboratorService collaboratorService)
    {
        this.collaboratorService = collaboratorService;
    }


    @Operation(
          description = "Returns a list of collaborators that worked in requested character comics",
          tags = {"collaborator-api-controller",}
    )
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = CollaboratorResponse.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid hero supplied"),
          @ApiResponse(responseCode = "404", description = "Provided hero not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error"),
          @ApiResponse(responseCode = "504", description = "Gateway timeout")})
    @GetMapping(value = "/collaborators/{hero}", produces = {"application/json"})
    public CompletableFuture<ResponseEntity<CollaboratorResponse>> getCollaboratorsByName(
          @Parameter(description = "The name of the marvel superhero", required = true) @PathVariable("hero") String hero)
    {
        try
        {
            return CompletableFuture.completedFuture(new ResponseEntity(collaboratorService.findCollaboratorByHero(hero), HttpStatus.OK));
        }
        catch (ApiException e)
        {
            return CompletableFuture.completedFuture(new ResponseEntity(e.toString(), HttpStatus.valueOf(e.getCode())));
        }
        catch (Exception e)
        {
            return CompletableFuture.completedFuture(new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
