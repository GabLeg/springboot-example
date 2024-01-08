package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GoodyControllerTest extends IntegrationTestParent {

    /*
        There is no needs to test everything because all the mechanics are from spring directly.
        We just make sure that /goodies exists.
    */

  private static final Long AN_ID = 100L;

  @Test
  void givenExistingGoodyId_whenGetGoody_thenReturn200() throws Exception {
    this.mockMvc.perform(get("/goodies/" + AN_ID)).andExpect(status().isOk());
  }
}
