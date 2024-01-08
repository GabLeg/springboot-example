package com.example.springbootexample.api.controllers.dto.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class InvoiceDto {
  private Long id;
  private LocalDateTime transactionDate;
  private BigDecimal total;
  private List<PurchaseItemDto> purchaseItemList;
}
