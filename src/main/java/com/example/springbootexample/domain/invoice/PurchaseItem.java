package com.example.springbootexample.domain.invoice;

import com.example.springbootexample.domain.Beer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
