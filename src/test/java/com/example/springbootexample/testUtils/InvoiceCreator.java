package com.example.springbootexample.testUtils;

import com.example.springbootexample.domain.object.Beer;
import com.example.springbootexample.domain.object.invoice.Invoice;
import com.example.springbootexample.domain.object.invoice.PurchaseItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceCreator {

  private static final BigDecimal AN_AMOUNT = new BigDecimal("12.04");
  private static final Integer A_QUANTITY = 4;

  public static Invoice create() {
    Invoice invoice = new Invoice();
    List<PurchaseItem> purchaseItemList = new ArrayList<>();
    Beer beer = new Beer();
    beer.setPrice(AN_AMOUNT);
    PurchaseItem purchaseItem = new PurchaseItem();
    purchaseItem.setBeer(beer);
    purchaseItem.setQuantity(A_QUANTITY);
    purchaseItemList.add(purchaseItem);
    invoice.setPurchaseItemList(purchaseItemList);
    return invoice;
  }
}
