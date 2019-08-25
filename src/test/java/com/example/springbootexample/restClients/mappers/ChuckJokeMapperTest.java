package com.example.springbootexample.restClients.mappers;

import com.example.springbootexample.ChuckJokeCreator;
import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.restClients.dto.ChuckJokeDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChuckJokeMapperTest extends TestParent {

    private ChuckJokeMapper chuckJokeMapper;

    @Before
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
