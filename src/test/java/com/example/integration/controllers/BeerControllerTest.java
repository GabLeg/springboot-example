package com.example.integration.controllers;

import com.example.integration.config.IntegrationTestParent;
import com.example.springbootexample.controllers.BeerController;
import com.example.springbootexample.domain.Beer;
import com.example.springbootexample.repositories.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = BeerController.class)
public class BeerControllerTest extends IntegrationTestParent {

    private static final Long AN_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;
    private static final Beer A_BEER = new Beer();

    @MockBean
    private BeerRepository beerRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenExistingBeerId_whenGetBeer_thenReturn200() throws Exception {
        when(beerRepositoryMock.findById(AN_ID)).thenReturn(Optional.of(A_BEER));
        this.mockMvc.perform(get("/beers/" + AN_ID.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUnknownBeerId_whenGetBeer_thenReturn404() throws Exception {
        when(beerRepositoryMock.findById(AN_ID)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/beers/" + UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNewBeer_whenCreateBeer_thenReturn201() throws Exception {
        when(beerRepositoryMock.save(any())).thenReturn(A_BEER);
        this.mockMvc.perform(post("/beers")
                        .content(objectMapper.writeValueAsString(A_BEER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
