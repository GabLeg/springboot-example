package com.example.springbootexample.api.controllers;

import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.infra.redis.MovieDocument;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RedisMovieControllerTest extends IntegrationTestParent {

  private static final String AN_ID = UUID.randomUUID().toString();
  private static final String UNKNOWN_ID = UUID.randomUUID().toString();
  private static final MovieDocument.MovieDocumentBuilder MOVIE_DOCUMENT_BUILDER = MovieDocument.builder()
                                                                                                .name("Titanic")
                                                                                                .duration(Duration.ofHours(2));

  @Test
  void givenExistingMovieId_whenFindMovieById_thenReturn200() throws Exception {
    MovieDocument movieDocument = MOVIE_DOCUMENT_BUILDER.id(AN_ID).build();
    movieRepository.save(movieDocument);

    this.mockMvc.perform(get("/movies/" + AN_ID)).andExpect(status().isOk());
  }

  @Test
  void givenUnknownMovieId_whenFindMovieById_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/movies/" + UNKNOWN_ID)).andExpect(status().isNotFound());
  }

  @Test
  void givenNewMovie_whenCreateMovie_thenReturn201() throws Exception {
    String payload = objectMapper.writeValueAsString(MOVIE_DOCUMENT_BUILDER.build());
    this.mockMvc.perform(post("/movies").content(payload).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
  }

}