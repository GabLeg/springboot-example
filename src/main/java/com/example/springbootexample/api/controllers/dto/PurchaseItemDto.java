package com.example.springbootexample.api.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseItemDto {
  private Integer quantity;
  private BeerDto beer;
}
