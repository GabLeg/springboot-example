package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.GameDto;
import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.infra.mongo.GameDocument;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MongoGameControllerTest extends IntegrationTestParent {

  private static final String AN_ID = UUID.randomUUID().toString();
  private static final String UNKNOWN_ID = UUID.randomUUID().toString();
  private static final String NAME = "Warcraft";
  private static final String PUBLISHER = "Blizzard";

  @Test
  void givenExistingGameId_whenFindGameById_thenReturn200() throws Exception {
    GameDocument gameDocument = GameDocument.builder().id(AN_ID).name(NAME).publisher(PUBLISHER).build();
    mongoTemplate.insert(gameDocument);

    MvcResult mvcResult = this.mockMvc.perform(get("/games/" + AN_ID)).andExpect(status().isOk()).andReturn();
    GameDto game = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GameDto.class);
    assertThat(game.getName()).isEqualTo(NAME);
    assertThat(game.getPublisher()).isEqualTo(PUBLISHER);
    assertThat(game.getId()).isEqualTo(AN_ID);
  }

  @Test
  void givenUnknownMovieId_whenFindMovieById_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/games/" + UNKNOWN_ID)).andExpect(status().isNotFound());
  }

  @Test
  void givenNewMovie_whenCreateMovie_thenReturn201() throws Exception {
    GameDto gameDto = GameDto.builder().name(NAME).publisher(PUBLISHER).build();
    String payload = objectMapper.writeValueAsString(gameDto);
    MvcResult mvcResult = this.mockMvc.perform(post("/games").content(payload).contentType(MediaType.APPLICATION_JSON))
                                      .andExpect(status().isCreated())
                                      .andReturn();

    GameDto createdGame = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GameDto.class);
    assertThat(createdGame.getName()).isEqualTo(NAME);
    assertThat(createdGame.getPublisher()).isEqualTo(PUBLISHER);
    assertThat(createdGame.getId()).isNotNull();
  }
}
