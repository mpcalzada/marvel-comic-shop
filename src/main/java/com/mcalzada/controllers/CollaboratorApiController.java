package com.mcalzada.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcalzada.model.CollaboratorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

@Controller
@Validated
@Log4j2
@Api(value = "Collaborator", description = "the Collaborator API")
@RequestMapping(value = "/marvel")
public class CollaboratorApiController
{

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CollaboratorApiController(ObjectMapper objectMapper, HttpServletRequest request)
    {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(
          value = "Get collaborator by name",
          nickname = "getCollaboratorsByName",
          notes = "Returns a list of collaborators that worked in requested character comics",
          response = CollaboratorResponse.class,
          tags = {"collaborator-api-controller",}
    )
    @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successful operation", response = CollaboratorResponse.class),
          @ApiResponse(code = 400, message = "Invalid hero supplied"),
          @ApiResponse(code = 404, message = "Provided hero not found"),
          @ApiResponse(code = 500, message = "Internal server error"),
          @ApiResponse(code = 504, message = "Gateway timeout")})
    @GetMapping(value = "/collaborators/{hero}", produces = {"application/json"})
    public CompletableFuture<ResponseEntity<CollaboratorResponse>> getCollaboratorsByName(
          @ApiParam(value = "The name of the marvel superhero", required = true) @PathVariable("hero") String hero)
    {
        try
        {
            return CompletableFuture.completedFuture(
                  new ResponseEntity<>(objectMapper.readValue("{\"empty\": false}", CollaboratorResponse.class),
                        HttpStatus.NOT_IMPLEMENTED));
        }
        catch (IOException e)
        {
            log.error("Couldn't serialize response for content type application/json", e);
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
