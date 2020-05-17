package com.example.springbootexample.testUtils;

import com.example.springbootexample.restClients.dto.ChuckJokeDTO;
import com.example.springbootexample.restClients.dto.ChuckJokeValueDTO;

public class ChuckJokeCreator {
    public static final String JOKE = "I AM A BANANA !";

    public static ChuckJokeDTO create() {
        ChuckJokeDTO chuckJokeDTO = new ChuckJokeDTO();
        chuckJokeDTO.setType("A_TYPE");
        ChuckJokeValueDTO chuckJokeValueDTO = new ChuckJokeValueDTO();
        chuckJokeValueDTO.setId(123);
        chuckJokeValueDTO.setJoke(JOKE);
        chuckJokeDTO.setValue(chuckJokeValueDTO);
        return chuckJokeDTO;
    }
}
