package com.example.springbootexample.api.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BreweryDto {
  private Long id;
  private String name;
  private String address;
  private String phone;
}
