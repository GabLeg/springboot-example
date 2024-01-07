package com.example.springbootexample.api.controllers;

import com.example.springbootexample.Popcorn;
import com.example.springbootexample.PopcornControllerGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.logging.Logger;

@GRpcService
public class GrpcPopcornController extends PopcornControllerGrpc.PopcornControllerImplBase {

  private static final Logger logger = Logger.getLogger(GrpcPopcornController.class.getName());

  @Override
  public void getPopcorn(Popcorn.PopcornRequest request, StreamObserver<Popcorn.PopcornReply> responseObserver) {
    logger.info("Requesting popcorn for the flavor : " + request.getFlavor());
    Popcorn.PopcornReply reply = Popcorn.PopcornReply.newBuilder().setQuantity("10 MT").build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
