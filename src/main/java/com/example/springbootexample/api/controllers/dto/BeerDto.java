package com.example.springbootexample.api.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BeerDto {
  private Long id;
  private String name;
  private String color;
  private Integer ibu;
  private Float alcohol;
  private BigDecimal price;
  private BreweryDto brewery;
  private BrewMasterDto creator;
}
