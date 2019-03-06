package com.example.springbootexample.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseItemDTO {
    private Integer quantity;
    private BeerDTO beer;
}
