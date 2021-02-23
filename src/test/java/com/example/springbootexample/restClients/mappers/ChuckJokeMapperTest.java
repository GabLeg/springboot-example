package com.example.springbootexample.restClients.mappers;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.restClients.dto.ChuckJokeDTO;
import com.example.springbootexample.testUtils.ChuckJokeCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChuckJokeMapperTest extends TestParent {

    @Test
    void givenChuckJokeDto_whenDtoToChuckJoke_thenReturnChuckJoke() {
        ChuckJokeDTO chuckJokeDTO = ChuckJokeCreator.create();

        ChuckJoke chuckJoke = ChuckJokeMapper.dtoToChuckJoke(chuckJokeDTO);

        assertEquals(ChuckJokeCreator.JOKE, chuckJoke.getJoke());
    }
}
