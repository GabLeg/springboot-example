package com.example.springbootexample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConfigService {

    @Value("${rest-client.chuck-jokes.url}")
    private String chuckJokeUrl;

    @Value("${rest-client.chuck-jokes.health-endpoint}")
    private String chuckJokeHealthEndpoint;

    @Value("${message.topic.name}")
    private String messageTopic;

    @Value("${kafka.topic.party.name}")
    private String partyTopic;

    @Value("${kafka.topic.invitation.name}")
    private String invitationTopic;
}
