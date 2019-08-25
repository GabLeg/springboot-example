package com.example.springbootexample.restClients;

import com.example.springbootexample.config.ConfigService;
import com.example.springbootexample.controllers.exceptions.ChuckNorrisException;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import com.example.springbootexample.restClients.dto.ChuckJokeDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.example.springbootexample.restClients.mappers.ChuckJokeMapper.dtoToChuckJoke;

@Component
public class ChuckJokesClient {

    private final RestTemplate restTemplate;
    private final ConfigService configService;

    public ChuckJokesClient(RestTemplate restTemplate, ConfigService configService) {
        this.restTemplate = restTemplate;
        this.configService = configService;
    }

    public void ping() throws RestClientException {
        restTemplate.getForObject(configService.getChuckJokeUrl() + configService.getChuckJokeHealthEndpoint(), Void.class);
    }

    public ChuckJoke getJoke() {
        ChuckJokeDTO joke;
        try {
            joke = restTemplate.getForObject(configService.getChuckJokeUrl() + "/jokes/random", ChuckJokeDTO.class);
        } catch (RestClientException e) {
            throw new ChuckNorrisException();
        }
        return dtoToChuckJoke(joke);
    }
}
