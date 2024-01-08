package com.example.springbootexample.infra.client;

import com.example.springbootexample.api.controllers.exceptions.ChuckNorrisException;
import com.example.springbootexample.config.ConfigService;
import com.example.springbootexample.domain.object.chuck.ChuckJoke;
import com.example.springbootexample.infra.client.dto.ChuckJokeDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.example.springbootexample.infra.client.mappers.ChuckJokeMapper.dtoToChuckJoke;

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
    ChuckJokeDto joke;
    try {
      joke = restTemplate.getForObject(configService.getChuckJokeUrl() + "/jokes/random", ChuckJokeDto.class);
    } catch (RestClientException e) {
      throw new ChuckNorrisException();
    }
    return dtoToChuckJoke(joke);
  }
}
