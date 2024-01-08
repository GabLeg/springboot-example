package com.example.springbootexample.domain.object.invoice;

import com.example.springbootexample.domain.object.Beer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PURCHASE_ITEM")
public class PurchaseItem {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "QUANTITY")
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "FK_BEER")
  private Beer beer;

  @ManyToOne
  @JoinColumn(name = "FK_INVOICE")
  private Invoice invoice;
}
