package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.NewMessageDto;
import com.example.springbootexample.config.ConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/messages")
public class JmsController {

  private final JmsTemplate jmsTemplate;
  private final ObjectMapper objectMapper;
  private final String messageTopic;

  public JmsController(JmsTemplate jmsTemplate, ObjectMapper objectMapper, ConfigService configService) {
    this.jmsTemplate = jmsTemplate;
    this.objectMapper = objectMapper;
    this.messageTopic = configService.getMessageTopic();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void sendMessage(@RequestBody NewMessageDto newMessage) throws Exception {
    jmsTemplate.convertAndSend(messageTopic, objectMapper.writeValueAsString(newMessage));
  }
}
