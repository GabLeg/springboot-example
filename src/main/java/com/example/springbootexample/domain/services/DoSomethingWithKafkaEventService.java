package com.example.springbootexample.domain.services;

import com.example.springbootexample.domain.object.Invitation;
import org.springframework.stereotype.Service;

@Service
public class DoSomethingWithKafkaEventService {

  public void doSomethingWithEvent(Invitation invitation) {
    System.out.println(invitation.toString());
  }
}
