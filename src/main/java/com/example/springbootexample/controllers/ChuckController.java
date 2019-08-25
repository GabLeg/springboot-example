package com.example.springbootexample.controllers;

import com.example.springbootexample.controllers.dto.ChuckJokeDTO;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.restClients.ChuckJokesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chuck")
public class ChuckController {

    private final ChuckJokesClient chuckJokesClient;
    private final ModelMapper mapper;

    @Autowired
    public ChuckController(ChuckJokesClient chuckJokesClient, ModelMapper mapper) {
        this.chuckJokesClient = chuckJokesClient;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChuckJokeDTO getChuckNorrisJoke() {
        return mapper.map(chuckJokesClient.getJoke(), ChuckJokeDTO.class);
    }
}
