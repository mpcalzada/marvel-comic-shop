package com.mcalzada.gateway;

import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiComicsResponse;
import com.mcalzada.model.api.ApiCreatorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "marvel-api", url = "${marvel.api.url}")
public interface MarvelGateway
{

    @RequestMapping(method = RequestMethod.GET, value = "/characters?apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiCharacterResponse getCharacters(
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );

    @RequestMapping(method = RequestMethod.GET, value = "/characters?name={name}&apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiCharacterResponse getCharacterByName(
          @PathVariable("name") String name,
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );

    @RequestMapping(method = RequestMethod.GET, value = "/characters/{characterId}/comics?apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiComicsResponse getComicsByCharacter(
          @PathVariable("characterId") Long characterId,
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );

    @RequestMapping(method = RequestMethod.GET, value = "/comics/{comicId}/creators?apikey={apiKey}&hash={hash}&ts={ts}", produces = "application/json")
    ApiCreatorResponse getComicCreators(
          @PathVariable("comicId") Long comicId,
          @PathVariable("apiKey") String apiKey,
          @PathVariable("hash") String hash,
          @PathVariable("ts") String ts
    );
}
