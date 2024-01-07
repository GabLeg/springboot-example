package com.example.springbootexample.infra.client.mappers;

import com.example.springbootexample.domain.object.chuck.ChuckJoke;
import com.example.springbootexample.infra.client.dto.ChuckJokeDto;

public class ChuckJokeMapper {

  public static ChuckJoke dtoToChuckJoke(ChuckJokeDto jokeDTO) {
    ChuckJoke chuckJoke = new ChuckJoke();
    chuckJoke.setJoke(jokeDTO.getValue().getJoke());
    return chuckJoke;
  }
}
