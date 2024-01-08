package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BeerControllerTest extends IntegrationTestParent {

  private static final Long AN_ID = 100L;
  private static final Long UNKNOWN_ID = 2L;

  @Test
  void givenExistingBeerId_whenGetBeer_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/beers/" + AN_ID)).andExpect(status().isOk());
  }

  @Test
  void givenUnknownBeerId_whenGetBeer_thenReturn404() throws Exception {
    this.mockMvc.perform(get("/beers/" + UNKNOWN_ID)).andExpect(status().isNotFound());
  }

  @Test
  void givenNewBeer_whenCreateBeer_thenReturn201() throws Exception {
    String newBeer = "{" + "\"name\":\"Matante\"," + "\"color\":\"blonde\"," + "\"ibu\":15," + "\"alcohol\":4.9," + "\"price\":5.00," + "\"brewery\":{" + "   \"id\":100" + "}," + "\"creator\":{" + "   \"id\":100" + "}" + "}";
    this.mockMvc.perform(post("/beers").content(newBeer).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
  }
}
