package com.example.springbootexample.api.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {
  private String id;
  private String name;
  private String duration;
}
