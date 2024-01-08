package com.example.springbootexample.domain.object.invoice;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "INVOICE")
public class Invoice {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "DATE")
  private LocalDateTime transactionDate;

  @Column(name = "TOTAL")
  private BigDecimal total;

  @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
  private List<PurchaseItem> purchaseItemList;

  public void calculateTotal() {
    this.total = new BigDecimal(0);
    for (PurchaseItem purchaseItem : purchaseItemList) {
      BigDecimal quantity = new BigDecimal(purchaseItem.getQuantity());
      total = total.add(purchaseItem.getBeer().getPrice().multiply(quantity));
    }
  }
}
