package com.example.springbootexample.kafka;

import com.example.springbootexample.InvitationEvent;
import com.example.springbootexample.config.IntegrationTestParent;
import com.example.springbootexample.domain.Invitation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

class KafkaInvitationConsumerTest extends IntegrationTestParent {

    private static final String LOCATION = "Montreal";
    private static final String DATE = "2021-12-31T23:59:59.999-05:00";
    private static final String RECIPIENT = "SpongeBob";

    @BeforeEach
    void resetInvitationTopic() {
        KAFKA.consumeFromAnEmbeddedTopic(kafkaConsumerFixture, INVITATION_TOPIC);
    }

    @Test
    void givenInvitationEvent_whenListenTopic_thenDoSomethingWithEvent() {
        InvitationEvent invitationEvent = InvitationEvent.newBuilder()
                                                         .setLocation(LOCATION)
                                                         .setDate(DATE)
                                                         .setRecipient(RECIPIENT)
                                                         .build();

        kafkaProducerFixture.send(INVITATION_TOPIC, invitationEvent.toString());

        ArgumentCaptor<Invitation> captor = ArgumentCaptor.forClass(Invitation.class);
        verify(doSomethingWithKafkaEventService, timeout(2000)).doSomethingWithEvent(captor.capture());
        assertEquals(LOCATION, captor.getValue().getLocation());
        assertEquals(DATE, captor.getValue().getDate());
        assertEquals(RECIPIENT, captor.getValue().getRecipient());
    }
}