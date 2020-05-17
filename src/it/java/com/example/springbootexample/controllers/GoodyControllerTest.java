package com.example.springbootexample.controllers;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoodyControllerTest extends IntegrationTestParent {

    /*
        There is no needs to test everything because all the mechanics are from spring directly.
        We just make sure that /goodies exists.
    */

    private static final Long AN_ID = 1L;

    @Test
    public void givenExistingGoodyId_whenGetGoody_thenReturn200() throws Exception {
        this.mockMvc.perform(get("/goodies/" + AN_ID.toString()))
                .andExpect(status().isOk());
    }
}
