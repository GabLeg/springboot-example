package com.example.springbootexample.api.controllers;

import com.example.springbootexample.Popcorn;
import com.example.springbootexample.PopcornControllerGrpc;
import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class GrpcPopcornControllerTest extends IntegrationTestParent {

  private static final String FLAVOR = "CHEESE";
  private static final String POPCORN_QTY = "10 MT";

  @Test
  void givenPopcornRequest_whenGetPopcorn_thenReturnPopcornQuantity() {
    Popcorn.PopcornRequest popcornRequest = Popcorn.PopcornRequest.newBuilder().setFlavor(FLAVOR).build();

    String quantity = PopcornControllerGrpc.newBlockingStub(grpcChannel).getPopcorn(popcornRequest).getQuantity();

    assertThat(quantity).isEqualTo(POPCORN_QTY);
  }
}
