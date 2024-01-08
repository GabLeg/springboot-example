package com.example.springbootexample.api.controllers.dto.jpa;

import com.example.springbootexample.api.controllers.dto.jpa.BeerDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseItemDto {
  private Integer quantity;
  private BeerDto beer;
}
