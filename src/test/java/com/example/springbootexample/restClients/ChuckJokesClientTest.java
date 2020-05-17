package com.example.springbootexample.restClients;

import com.example.springbootexample.config.ConfigService;
import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.controllers.exceptions.ChuckNorrisException;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ChuckJokesClientTest extends TestParent {

    private static final String CHUCK_URL = "http://localhost:8000";
    private static final String CHUCK_HEALTH_ENDPOINT = "/health";

    private ChuckJokesClient chuckJokesClient;
    private MockRestServiceServer mockServer;

    @Mock
    private ConfigService configService;

    @BeforeEach
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        when(configService.getChuckJokeUrl()).thenReturn(CHUCK_URL);
        when(configService.getChuckJokeHealthEndpoint()).thenReturn(CHUCK_HEALTH_ENDPOINT);
        chuckJokesClient = new ChuckJokesClient(restTemplate, configService);
        //Create a fake Rest Service server
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }

    @Test
    public void whenPing_thenHttpCallWasMade() {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + configService.getChuckJokeHealthEndpoint()))
                .andRespond(withSuccess());

        chuckJokesClient.ping();

        mockServer.verify();
    }

    @Test
    public void whenGetJoke_thenReturnChuckJoke() {
        String apiResponse = "{ \"type\": \"success\", \"value\": { \"id\": 123, \"joke\": \"A_JOKE\"}}";
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                .andRespond(withSuccess(apiResponse, MediaType.APPLICATION_JSON));

        ChuckJoke chuckJoke = chuckJokesClient.getJoke();

        assertEquals("A_JOKE", chuckJoke.getJoke());
    }

    @Test
    public void givenHttpError_whenGetJoke_thenThrowsChuckNorrisException() {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                .andRespond(withServerError());

        Assertions.assertThrows(ChuckNorrisException.class, () ->
                chuckJokesClient.getJoke());
    }
}
