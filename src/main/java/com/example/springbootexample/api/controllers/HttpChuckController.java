package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.ChuckJokeDto;
import com.example.springbootexample.infra.client.ChuckJokesClient;
import com.example.springbootexample.mappers.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chuck")
public class HttpChuckController {

  private final ChuckJokesClient chuckJokesClient;
  private final ModelMapper mapper;

  @Autowired
  public HttpChuckController(ChuckJokesClient chuckJokesClient, ModelMapper mapper) {
    this.chuckJokesClient = chuckJokesClient;
    this.mapper = mapper;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ChuckJokeDto getChuckNorrisJoke() {
    return mapper.map(chuckJokesClient.getJoke(), ChuckJokeDto.class);
  }
}
