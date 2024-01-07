package com.example.springbootexample.api.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto {

  private String location;
  private String date;
  private int maximumCapacity;
}
