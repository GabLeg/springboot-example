package com.example.springbootexample.restClients.mappers;

import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.restClients.dto.ChuckJokeDTO;

public class ChuckJokeMapper {

    public static ChuckJoke dtoToChuckJoke(ChuckJokeDTO jokeDTO) {
        ChuckJoke chuckJoke = new ChuckJoke();
        chuckJoke.setJoke(jokeDTO.getValue().getJoke());
        return chuckJoke;
    }
}
