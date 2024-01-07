package com.example.springbootexample.api.kafka;

import com.example.springbootexample.InvitationEvent;
import com.example.springbootexample.domain.object.Invitation;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.domain.services.DoSomethingWithKafkaEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaInvitationConsumer {

  private final DoSomethingWithKafkaEventService service;
  private final ObjectMapper objectMapper;
  private final ModelMapper modelMapper;

  public KafkaInvitationConsumer(DoSomethingWithKafkaEventService service, ObjectMapper objectMapper, ModelMapper modelMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
    this.modelMapper = modelMapper;
  }

  @KafkaListener(topics = "#{'${kafka.topic.invitation.name}'}")
  public void listenTopic(@Payload String message) throws Exception {
    InvitationEvent invitationEvent = objectMapper.readValue(message, InvitationEvent.class);
    service.doSomethingWithEvent(modelMapper.map(invitationEvent, Invitation.class));
  }
}
