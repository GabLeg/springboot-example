package com.example.springbootexample.restClients.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChuckJokeDTO {
    private String type;
    private ChuckJokeValueDTO value;
}
