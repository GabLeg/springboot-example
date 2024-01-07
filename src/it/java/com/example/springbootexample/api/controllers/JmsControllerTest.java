package com.example.springbootexample.api.controllers;

import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.api.controllers.dto.NewMessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class JmsControllerTest extends IntegrationTestParent {

  private static final String TITLE = "Avengers new mission.";
  private static final String MESSAGE = "This message will self-destruct in 5 seconds.";

  @Test
  void givenNewMessage_whenPostSendMessage_thenReturn204() throws Exception {
    NewMessageDto newMessageDto = new NewMessageDto(TITLE, MESSAGE);

    this.mockMvc.perform(post("/messages").content(objectMapper.writeValueAsString(newMessageDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    String stringMessage = (String) jmsTemplate.receiveAndConvert(configService.getMessageTopic());
    NewMessageDto message = objectMapper.readValue(stringMessage, NewMessageDto.class);
    assertEquals(TITLE, message.getTitle());
    assertEquals(MESSAGE, message.getMessage());
  }
}