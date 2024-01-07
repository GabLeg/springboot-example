package com.example.springbootexample.testUtils;

import com.example.springbootexample.infra.client.dto.ChuckJokeDto;
import com.example.springbootexample.infra.client.dto.ChuckJokeValueDto;

public class ChuckJokeCreator {
  public static final String JOKE = "I AM A BANANA !";

  public static ChuckJokeDto create() {
    ChuckJokeDto chuckJokeDTO = new ChuckJokeDto();
    chuckJokeDTO.setType("A_TYPE");
    ChuckJokeValueDto chuckJokeValueDTO = new ChuckJokeValueDto();
    chuckJokeValueDTO.setId(123);
    chuckJokeValueDTO.setJoke(JOKE);
    chuckJokeDTO.setValue(chuckJokeValueDTO);
    return chuckJokeDTO;
  }
}
