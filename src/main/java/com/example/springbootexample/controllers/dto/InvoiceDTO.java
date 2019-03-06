package com.example.springbootexample.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class InvoiceDTO {
    private Long id;
    private LocalDateTime transactionDate;
    private BigDecimal total;
    private List<PurchaseItemDTO> purchaseItemList;
}
