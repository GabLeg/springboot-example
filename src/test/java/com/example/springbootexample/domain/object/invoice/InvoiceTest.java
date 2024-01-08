package com.example.springbootexample.domain.object.invoice;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.testUtils.InvoiceCreator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.google.common.truth.Truth.assertThat;

class InvoiceTest extends TestParent {

  private static final BigDecimal INVOICE_TOTAL = new BigDecimal("48.16");

  @Test
  void givenInvoice_whenCalculateTotal_thenTotalIsGood() {
    Invoice invoice = InvoiceCreator.create();

    invoice.calculateTotal();

    assertThat(invoice.getTotal()).isEqualTo(INVOICE_TOTAL);
  }
}
