package com.example.springbootexample.controllers.dto;

import com.example.springbootexample.domain.brewMaster.Experience;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrewMasterDTO {
    private Long id;
    private String name;
    private Experience experience;
}
