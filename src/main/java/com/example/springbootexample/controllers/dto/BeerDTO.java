package com.example.springbootexample.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BeerDTO {
    private Long id;
    private String name;
    private String color;
    private Integer ibu;
    private Float alcohol;
    private BigDecimal price;
    private BreweryDTO brewery;
    private BrewMasterDTO creator;
}
