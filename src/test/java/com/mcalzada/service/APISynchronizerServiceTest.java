package com.mcalzada.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mcalzada.gateway.MarvelGateway;
import com.mcalzada.model.api.ApiCharacterResponse;
import com.mcalzada.model.api.ApiCharacterResponse.ApiCharacterData;
import com.mcalzada.model.api.ApiCharacterResponse.ApiCharacterResult;
import com.mcalzada.model.api.ApiComicsResponse;
import com.mcalzada.model.api.ApiComicsResponse.ApiCharacter;
import com.mcalzada.model.api.ApiComicsResponse.ApiComicData;
import com.mcalzada.model.api.ApiComicsResponse.ApiCreators;
import com.mcalzada.model.api.ApiComicsResponse.Item;
import com.mcalzada.model.api.ApiComicsResponse.Results;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class APISynchronizerServiceTest
{

    private APISynchronizerService apiSynchronizerService;
    private CollaboratorService collaboratorService;
    private CharacterService characterService;

    @BeforeEach
    void setUp()
    {
        ApiCharacterResponse characterOkResponse = buildCharacterResponse();
        ApiComicsResponse apiComicsResponse = buildComicResponse();

        MarvelGateway marvelGateway = mock(MarvelGateway.class);
        collaboratorService = mock(CollaboratorService.class);
        characterService = mock(CharacterService.class);

        when(marvelGateway.getCharacterByName(anyString(), Mockito.any(), Mockito.any(), Mockito.any()))
              .thenReturn(characterOkResponse);
        when(marvelGateway.getComicsByCharacter(anyLong(), Mockito.any(), Mockito.any(), Mockito.any()))
              .thenReturn(apiComicsResponse);

        apiSynchronizerService = new APISynchronizerService(marvelGateway, characterService, collaboratorService);
    }

    @Test
    void should_create_character_when_API_response_is_OK()
    {
        doNothing().when(collaboratorService).createCollaborators(anyList());

        apiSynchronizerService.synchronizeHero("Iron Man");

        verify(collaboratorService, times(2)).createCollaborators(anyList());
        verify(characterService, times(2)).createCharacters(anyList());
    }

    private ApiCharacterResponse buildCharacterResponse()
    {
        return ApiCharacterResponse.builder()
              .code(200)
              .status("OK")
              .data(
                    ApiCharacterData.builder()
                          .count(1)
                          .offset(0L)
                          .limit(1)
                          .total(1)
                          .results(
                                Collections.singletonList(
                                      ApiCharacterResult.builder().id(1009368L).name("Iron Man").build()
                                )
                          )
                          .build()
              ).build();
    }

    private ApiComicsResponse buildComicResponse()
    {
        return ApiComicsResponse.builder()
              .code(200)
              .status("OK")
              .data(
                    ApiComicData.builder()
                          .count(1)
                          .offset(0L)
                          .limit(1)
                          .total(1)
                          .results(
                                Arrays.asList(
                                      Results.builder()
                                            .id(27238L)
                                            .title("Wolverine Saga (2009) #7")
                                            .format("Comic")
                                            .characters(ApiCharacter.builder()
                                                  .items(Arrays.asList(
                                                              Item.builder()
                                                                    .name("Iron Man")
                                                                    .resourceURI("http://gateway.marvel.com/v1/public/characters/1009368")
                                                                    .build(),
                                                              Item.builder()
                                                                    .name("Wolverine")
                                                                    .resourceURI("http://gateway.marvel.com/v1/public/characters/1009718")
                                                                    .build()
                                                        )
                                                  ).build())
                                            .creators(ApiCreators.builder()
                                                  .items(Arrays.asList(
                                                              Item.builder()
                                                                    .name("Jeff Youngquist")
                                                                    .role("edito")
                                                                    .resourceURI("http://gateway.marvel.com/v1/public/creators/4430")
                                                                    .build()
                                                        )
                                                  ).build()
                                            ).build(),
                                      Results.builder()
                                            .id(98402L)
                                            .title("Captain America/Iron Man (2021) #4")
                                            .format("Comic")
                                            .characters(ApiCharacter.builder()
                                                  .items(Arrays.asList(
                                                              Item.builder()
                                                                    .name("Iron Man")
                                                                    .resourceURI("http://gateway.marvel.com/v1/public/characters/1009368")
                                                                    .build(),
                                                              Item.builder()
                                                                    .name("Captain America")
                                                                    .resourceURI("http://gateway.marvel.com/v1/public/characters/1009220")
                                                                    .build()
                                                        )
                                                  ).build())
                                            .creators(ApiCreators.builder()
                                                  .items(Arrays.asList(
                                                        Item.builder()
                                                              .name("Vc Joe Caramagna")
                                                              .role("letterer")
                                                              .resourceURI("http://gateway.marvel.com/v1/public/creators/5251")
                                                              .build(),
                                                        Item.builder()
                                                              .name("Derek Landy")
                                                              .role("writer")
                                                              .resourceURI("http://gateway.marvel.com/v1/public/creators/12861")
                                                              .build(),
                                                        Item.builder()
                                                              .name("Rachelle Rosenberg")
                                                              .role("colorist")
                                                              .resourceURI("http://gateway.marvel.com/v1/public/creators/12991")
                                                              .build()
                                                  )).build()
                                            ).build()
                                )).build()
              ).build();
    }
}