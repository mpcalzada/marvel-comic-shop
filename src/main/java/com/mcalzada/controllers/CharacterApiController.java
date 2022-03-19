package com.mcalzada.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcalzada.model.entity.Character;
import com.mcalzada.model.CharactersResponse;
import com.mcalzada.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
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
@RequestMapping(value = "/marvel")
@Validated
@Log4j2
public class CharacterApiController
{

    private ObjectMapper objectMapper;
    private CharacterService characterService;

    @Autowired
    public CharacterApiController(ObjectMapper objectMapper, CharacterService marvelGateway)
    {
        this.objectMapper = objectMapper;
        this.characterService = marvelGateway;
    }

    @Operation(
          summary = "Returns other heroes interactions with requested character",
          tags = {"character-api-controller",}
    )
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = CharactersResponse.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid hero name supplied"),
          @ApiResponse(responseCode = "404", description = "Provided hero not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error"),
          @ApiResponse(responseCode = "504", description = "Gateway timeout")})
    @GetMapping(value = "/characters/{hero}", produces = {"application/json"})
    public CompletableFuture<ResponseEntity<CharactersResponse>> getCharactersByName(
          @Parameter(description = "The name of the marvel superhero", required = true) @PathVariable("hero") String hero)
    {
        Optional<Character> optionalCharacter = characterService.findCharacterByName(hero);
        if (optionalCharacter.isPresent())
        {
            return CompletableFuture.completedFuture(new ResponseEntity(optionalCharacter.get(), HttpStatus.OK));
        }
        return CompletableFuture.completedFuture(new ResponseEntity("Not Found", HttpStatus.NOT_FOUND));
    }
}