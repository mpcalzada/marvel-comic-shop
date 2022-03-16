package com.mcalzada.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcalzada.model.CharactersResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

@Controller
@Api(value = "Character", description = "the Character API")
@RequestMapping(value = "/marvel")
@Validated
@Log4j2
public class CharacterApiController
{

    private ObjectMapper objectMapper;

    @Autowired
    public CharacterApiController(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    @ApiOperation(
          value = "Get charactere by name",
          nickname = "getCharactersByName",
          notes = "Returns other heroes interactions with requested character",
          response = CharactersResponse.class,
          tags = {"character-api-controller",}
    )
    @ApiResponses(value = {
          @ApiResponse(code = 200, message = "successful operation", response = CharactersResponse.class),
          @ApiResponse(code = 400, message = "Invalid hero name supplied"),
          @ApiResponse(code = 404, message = "Provided hero not found"),
          @ApiResponse(code = 500, message = "Internal server error"),
          @ApiResponse(code = 504, message = "Gateway timeout")})
    @GetMapping(value = "/characters/{hero}", produces = {"application/json"})
    public CompletableFuture<ResponseEntity<CharactersResponse>> getCharactersByName(
          @ApiParam(value = "The name of the marvel superhero", required = true) @PathVariable("hero") String hero)
    {
        try
        {
            return CompletableFuture.completedFuture(
                  new ResponseEntity<>(objectMapper.readValue("{\"empty\": false}", CharactersResponse.class),
                        HttpStatus.NOT_IMPLEMENTED));
        }
        catch (IOException e)
        {
            log.error("Couldn't serialize response for content type application/json", e);
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}