package com.example.springbootexample.api.controllers;

import com.example.springbootexample.PartyEvent;
import com.example.springbootexample.api.controllers.dto.PartyDto;
import com.example.springbootexample.config.ConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/parties")
public class PartyController {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final String partyTopic;

  public PartyController(KafkaTemplate<String, String> kafkaTemplate, ConfigService configService) {
    this.kafkaTemplate = kafkaTemplate;
    this.partyTopic = configService.getPartyTopic();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void sendParty(@RequestBody PartyDto partyDto) {
    PartyEvent event = PartyEvent.newBuilder()
                                 .setLocation(partyDto.getLocation())
                                 .setDate(partyDto.getDate())
                                 .setMaximumCapacity(partyDto.getMaximumCapacity())
                                 .build();
    kafkaTemplate.send(partyTopic, event.toString());
  }
}
