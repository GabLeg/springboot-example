package com.example.springbootexample.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BreweryDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
