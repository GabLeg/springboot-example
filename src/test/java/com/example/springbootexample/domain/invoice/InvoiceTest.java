package com.example.springbootexample.domain.invoice;

import com.example.springbootexample.InvoiceCreator;
import com.example.springbootexample.config.TestParent;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class InvoiceTest extends TestParent {

    private static final BigDecimal INVOICE_TOTAL = new BigDecimal("48.16");

    @Test
    public void givenInvoice_whenCalculateTotal_thenTotalIsGood() {
        Invoice invoice = InvoiceCreator.create();

        invoice.calculateTotal();

        assertEquals(INVOICE_TOTAL, invoice.getTotal());
    }
}