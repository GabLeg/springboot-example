package com.example.springbootexample.restClients.mappers;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.restClients.dto.ChuckJokeDTO;
import com.example.springbootexample.testUtils.ChuckJokeCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChuckJokeMapperTest extends TestParent {

    private ChuckJokeMapper chuckJokeMapper;

    @BeforeEach
    public void init() {
        chuckJokeMapper = new ChuckJokeMapper();
    }

    @Test
    public void givenChuckJokeDto_whenDtoToChuckJoke_thenReturnChuckJoke() {
        ChuckJokeDTO chuckJokeDTO = ChuckJokeCreator.create();

        ChuckJoke chuckJoke = chuckJokeMapper.dtoToChuckJoke(chuckJokeDTO);

        assertEquals(ChuckJokeCreator.JOKE, chuckJoke.getJoke());
    }
}
