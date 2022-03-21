package com.mcalzada.gateway;

import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiComicsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class is used as a gateway service for fetching data from Marvel API. Built over {@link feign.Feign}
 *
 * @author Marco Calzada
 * @version 1.0
 */
@FeignClient(value = "marvel-api", url = "${marvel.api.url}")
public interface MarvelGateway
{

    /**
     * Fetch the hole list of characters registered over Marvel API.
     *
     * @param apiKey authentication provided API key for connection to Marvel API
     * @param hash   authentication generated hash
     * @param ts     current timestamp
     * @return {@link ApiCharacterResponse} DTO with response mapped data
     */
    @RequestMapping(method = RequestMethod.GET, value = "/characters?apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiCharacterResponse getCharacters(
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );

    /**
     * Fetch the character information detail
     *
     * @param name   requested hero name
     * @param apiKey authentication provided API key for connection to Marvel API
     * @param hash   authentication generated hash
     * @param ts     current timestamp
     * @return {@link ApiCharacterResponse} DTO with response mapped data
     */
    @RequestMapping(method = RequestMethod.GET, value = "/characters?name={name}&apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiCharacterResponse getCharacterByName(
          @PathVariable("name") String name,
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );

    /**
     * Fetch the character´s comic information detail
     *
     * @param characterId marvel API character id
     * @param offset      registers pagination offset
     * @param apiKey      authentication provided API key for connection to Marvel API
     * @param hash        authentication generated hash
     * @param ts          current timestamp
     * @return {@link ApiCharacterResponse} DTO with response mapped data
     */
    @RequestMapping(method = RequestMethod.GET, value = "/characters/{characterId}/comics?limit=100&offset={offset}&apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiComicsResponse getComicsByCharacter(
          @PathVariable("characterId") Long characterId,
          @PathVariable("offset") Long offset,
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );
}
