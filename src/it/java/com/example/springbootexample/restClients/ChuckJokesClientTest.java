package com.example.springbootexample.restClients;

import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.controllers.exceptions.ChuckNorrisException;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class ChuckJokesClientTest extends IntegrationTestParent {

    private ChuckJokesClient chuckJokesClient;

    @BeforeEach
    void initClient() {
        chuckJokesClient = new ChuckJokesClient(restTemplate, configService);
    }

    @Test
    void whenPing_thenHttpCallWasMade() {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + configService.getChuckJokeHealthEndpoint()))
                  .andRespond(withSuccess());

        chuckJokesClient.ping();

        mockServer.verify();
    }

    @Test
    void whenGetJoke_thenReturnChuckJoke() {
        String apiResponse = "{ \"type\": \"success\", \"value\": { \"id\": 123, \"joke\": \"A_JOKE\"}}";
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                  .andRespond(withSuccess(apiResponse, MediaType.APPLICATION_JSON));

        ChuckJoke chuckJoke = chuckJokesClient.getJoke();

        assertEquals("A_JOKE", chuckJoke.getJoke());
    }

    @Test
    void givenHttpError_whenGetJoke_thenThrowsChuckNorrisException() {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                  .andRespond(withServerError());

        Assertions.assertThrows(ChuckNorrisException.class, () ->
                chuckJokesClient.getJoke());
    }
}
