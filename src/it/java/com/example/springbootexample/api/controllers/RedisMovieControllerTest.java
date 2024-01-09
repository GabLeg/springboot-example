package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.MovieDto;
import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.infra.redis.MovieDocument;
import com.example.springbootexample.infra.redis.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Duration;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RedisMovieControllerTest extends IntegrationTestParent {

  private static final String AN_ID = UUID.randomUUID().toString();
  private static final String UNKNOWN_ID = UUID.randomUUID().toString();
  private static final String NAME = "Titanic";
  private static final Duration DURATION = Duration.ofHours(2);

  @Autowired
  private MovieRepository movieRepository;

  @Test
  void givenExistingMovieId_whenFindMovieById_thenReturn200() throws Exception {
    MovieDocument movieDocument = MovieDocument.builder().name(NAME).duration(DURATION).build();
    movieRepository.save(movieDocument);

    MvcResult mvcResult = this.mockMvc.perform(get("/movies/" + AN_ID)).andExpect(status().isOk()).andReturn();
    MovieDto movie = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MovieDto.class);
    assertThat(movie.getName()).isEqualTo(NAME);
    assertThat(movie.getDuration()).isEqualTo(DURATION.toString());
    assertThat(movie.getId()).isEqualTo(AN_ID);
  }

  @Test
  void givenUnknownMovieId_whenFindMovieById_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/movies/" + UNKNOWN_ID)).andExpect(status().isNotFound());
  }

  @Test
  void givenNewMovie_whenCreateMovie_thenReturn201() throws Exception {
    MovieDto movieDto = MovieDto.builder().name(NAME).duration(DURATION.toString()).build();
    String payload = objectMapper.writeValueAsString(movieDto);
    MvcResult mvcResult = this.mockMvc.perform(post("/movies").content(payload).contentType(MediaType.APPLICATION_JSON))
                                      .andExpect(status().isCreated())
                                      .andReturn();

    MovieDto createdMovie = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MovieDto.class);
    assertThat(createdMovie.getName()).isEqualTo(NAME);
    assertThat(createdMovie.getDuration()).isEqualTo(DURATION.toString());
    assertThat(createdMovie.getId()).isNotNull();
  }

}