package com.example.springbootexample.services;

import com.example.springbootexample.domain.Invitation;
import org.springframework.stereotype.Service;

@Service
public class DoSomethingWithKafkaEventService {

    public void doSomethingWithEvent(Invitation invitation) {
        System.out.println(invitation.toString());
    }
}
