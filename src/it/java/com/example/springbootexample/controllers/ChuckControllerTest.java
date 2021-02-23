package com.example.springbootexample.controllers;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChuckControllerTest extends IntegrationTestParent {

    @Test
    void givenHttpCall_whenGetChuckNorrisJoke_thenReturnJoke() throws Exception {
        String apiResponse = "{ \"type\": \"success\", \"value\": { \"id\": 123, \"joke\": \"A_JOKE\"}}";
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                  .andRespond(withSuccess(apiResponse, MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/chuck"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("{\"joke\":\"A_JOKE\"}"));
    }

    @Test
    void givenHttpCall_whenExternalServiceInError_thenReturnHttp500() throws Exception {
        mockServer.expect(requestTo(configService.getChuckJokeUrl() + "/jokes/random"))
                  .andRespond(withServerError());

        this.mockMvc.perform(get("/chuck"))
                    .andExpect(status().isInternalServerError());
    }
}
