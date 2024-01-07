package com.example.springbootexample.infra.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChuckJokeDto {
  private String type;
  private ChuckJokeValueDto value;
}
