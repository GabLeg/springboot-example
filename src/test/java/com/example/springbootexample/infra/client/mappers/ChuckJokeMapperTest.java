package com.example.springbootexample.infra.client.mappers;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.object.chuck.ChuckJoke;
import com.example.springbootexample.infra.client.dto.ChuckJokeDto;
import com.example.springbootexample.testUtils.ChuckJokeCreator;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class ChuckJokeMapperTest extends TestParent {

  @Test
  void givenChuckJokeDto_whenDtoToChuckJoke_thenReturnChuckJoke() {
    ChuckJokeDto chuckJokeDTO = ChuckJokeCreator.create();

    ChuckJoke chuckJoke = ChuckJokeMapper.dtoToChuckJoke(chuckJokeDTO);

    assertThat(chuckJoke.getJoke()).isEqualTo(ChuckJokeCreator.JOKE);
  }
}
