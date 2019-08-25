package com.example.springbootexample.restClients;

import com.example.springbootexample.config.ConfigService;
import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.controllers.exceptions.ChuckNorrisException;
import com.example.springbootexample.domain.chuck.ChuckJoke;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ChuckJokesClientTest extends TestParent {
    private ChuckJokesClient chuckJokesClient;
    private MockRestServiceServer mockServer;

    @Autowired
    private ConfigService configService;

    @Before
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
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

    @Test(expected = ChuckNorrisException.class)
    public void givenHttpError_whenGetJoke_thenThrowsChuckNorrisException() {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                .andRespond(withServerError());

        chuckJokesClient.getJoke();
    }
}
