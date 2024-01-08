package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BreweryControllerTest extends IntegrationTestParent {

  private static final Long EXISTING_ID = 100L;
  private static final Long UNKNOWN_ID = 2L;

  @Test
  void givenExistingBreweryId_whenGetBrewery_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/breweries/" + EXISTING_ID)).andExpect(status().isOk());
  }

  @Test
  void givenUnknownBreweryId_whenGetBrewery_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/breweries/" + UNKNOWN_ID)).andExpect(status().isNotFound());
  }

  @Test
  void givenNothing_whenGetBreweries_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/breweries")).andExpect(status().isOk());
  }

  @Test
  void givenNewBrewery_whenCreateBrewery_thenReturn201() throws Exception {
    String newBrewery = "{" + "\"name\":\"Archibald\"," + "\"address\":\"1021, boulevard du Lac\"," + "\"phone\":\"418-841-2224\"" + "}";
    this.mockMvc.perform(post("/breweries").content(newBrewery).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
  }

  @Test
  void givenBreweryIdAndBrewMaster_whenAddBrewMaster_thenReturn204() throws Exception {
    String newBrewMaster = "{" + "\"name\":\"Roger\"," + "\"experience\":\"MASTER\"" + "}";
    this.mockMvc.perform(put("/breweries/" + EXISTING_ID + "/brewMasters").content(newBrewMaster).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
  }

  @Test
  void givenUnknownBreweryIdAndBrewMaster_whenAddBrewMaster_thenReturn404() throws Exception {
    String newBrewMaster = "{" + "\"name\":\"Roger\"," + "\"experience\":\"MASTER\"" + "}";
    this.mockMvc.perform(put("/breweries/" + UNKNOWN_ID + "/brewMasters").content(newBrewMaster).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
  }

  @Test
  void givenBreweryId_whenGetEmployees_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/breweries/" + EXISTING_ID + "/brewMasters")).andExpect(status().isOk());
  }

  @Test
  void givenUnknownBreweryId_whenGetEmployees_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/breweries/" + UNKNOWN_ID + "/brewMasters")).andExpect(status().isNotFound());
  }

  @Test
  void givenBreweryId_whenGetBeers_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/breweries/" + EXISTING_ID + "/beers")).andExpect(status().isOk());
  }

  @Test
  void givenUnknownBreweryId_whenGetBeers_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/breweries/" + UNKNOWN_ID + "/beers")).andExpect(status().isNotFound());
  }
}
