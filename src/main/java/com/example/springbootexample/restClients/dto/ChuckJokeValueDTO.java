package com.example.springbootexample.restClients.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChuckJokeValueDTO {
    private Integer id;
    private String joke;
}
