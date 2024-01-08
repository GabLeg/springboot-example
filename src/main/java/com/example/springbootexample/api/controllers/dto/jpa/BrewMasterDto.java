package com.example.springbootexample.api.controllers.dto.jpa;

import com.example.springbootexample.domain.object.brewMaster.Experience;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrewMasterDto {
  private Long id;
  private String name;
  private Experience experience;
}
