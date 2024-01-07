package com.example.springbootexample.infra.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChuckJokeValueDto {
  private Integer id;
  private String joke;
}
