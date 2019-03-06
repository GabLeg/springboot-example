package com.example.integration.controllers;

import com.example.integration.config.IntegrationTestParent;
import com.example.springbootexample.controllers.BrewMasterController;
import com.example.springbootexample.domain.brewMaster.BrewMaster;
import com.example.springbootexample.repositories.BrewMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = BrewMasterController.class)
public class BrewMasterControllerTest extends IntegrationTestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;
    private static final BrewMaster A_BREW_MASTER = new BrewMaster();

    @MockBean
    private BrewMasterRepository brewMasterRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenBrewMasterId_whenGetBrewMaster_thenReturn200() throws Exception {
        when(brewMasterRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(A_BREW_MASTER));
        this.mockMvc.perform(get("/brewMasters/"+ EXISTING_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUnknownBrewMasterId_whenGetBrewMaster_thenReturn404() throws Exception {
        when(brewMasterRepositoryMock.findById(UNKNOWN_ID)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/brewMasters/"+ UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNewBrewMaster_whenCreateBrewMaster_thenReturn201() throws Exception {
        when(brewMasterRepositoryMock.save(A_BREW_MASTER)).thenReturn(A_BREW_MASTER);
        this.mockMvc.perform(post("/brewMasters")
                        .content(objectMapper.writeValueAsString(A_BREW_MASTER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
