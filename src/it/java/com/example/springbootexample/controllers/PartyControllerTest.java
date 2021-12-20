package com.example.springbootexample.controllers;

import com.example.springbootexample.PartyEvent;
import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.controllers.dto.PartyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PartyControllerTest extends IntegrationTestParent {

    private static final String LOCATION = "Quebec";
    private static final String DATE = "2021-12-31T23:00:00.000-05:00";
    private static final int MAX_CAPACITY = 10;

    @BeforeEach
    void resetPartyTopic() {
        KAFKA.consumeFromAnEmbeddedTopic(kafkaConsumerFixture, PARTY_TOPIC);
    }

    @Test
    void givenNewMessage_whenPostSendMessage_thenReturn204() throws Exception {
        PartyDto partyDto = new PartyDto(LOCATION, DATE, MAX_CAPACITY);
        this.mockMvc.perform(post("/parties")
                    .content(objectMapper.writeValueAsString(partyDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

        ConsumerRecords<String, String> poll = kafkaConsumerFixture.poll(Duration.ofSeconds(5));
        Iterable<ConsumerRecord<String, String>> records = poll.records(PARTY_TOPIC);
        List<PartyEvent> partyEvents = new ArrayList<>();
        records.forEach(record -> {
            try {
                partyEvents.add(objectMapper.readValue(record.value(), PartyEvent.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        PartyEvent event = partyEvents.get(0);
        assertEquals(LOCATION, event.getLocation());
        assertEquals(DATE, event.getDate());
        assertEquals(MAX_CAPACITY, event.getMaximumCapacity());
    }
}