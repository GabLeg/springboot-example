package com.example.springbootexample.config.dependanciesHealthCheck;

import com.example.springbootexample.infra.client.ChuckJokesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class ChuckJokeHealth implements HealthIndicator {

  private final ChuckJokesClient chuckJokesClient;

  @Autowired
  public ChuckJokeHealth(ChuckJokesClient chuckJokesClient) {
    this.chuckJokesClient = chuckJokesClient;
  }

  @Override
  public Health health() {
    try {
      chuckJokesClient.ping();
    } catch (RestClientException e) {
      return Health.down().build();
    }
    return Health.up().build();
  }
}
