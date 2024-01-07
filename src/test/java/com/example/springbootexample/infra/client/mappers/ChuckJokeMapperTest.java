package com.example.springbootexample.infra.client.mappers;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.object.chuck.ChuckJoke;
import com.example.springbootexample.infra.client.dto.ChuckJokeDto;
import com.example.springbootexample.testUtils.ChuckJokeCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChuckJokeMapperTest extends TestParent {

  @Test
  void givenChuckJokeDto_whenDtoToChuckJoke_thenReturnChuckJoke() {
    ChuckJokeDto chuckJokeDTO = ChuckJokeCreator.create();

    ChuckJoke chuckJoke = ChuckJokeMapper.dtoToChuckJoke(chuckJokeDTO);

    assertEquals(ChuckJokeCreator.JOKE, chuckJoke.getJoke());
  }
}
