package com.example.springbootexample.services;

import com.example.springbootexample.InvoiceCreator;
import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.invoice.Invoice;
import com.example.springbootexample.repositories.InvoiceRepository;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvoiceServiceTest extends TestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;
    private static final Invoice AN_INVOICE = InvoiceCreator.create();

    private InvoiceService invoiceService;
    private InvoiceRepository invoiceRepositoryMock;

    @Before
    public void init() {
        invoiceRepositoryMock = mock(InvoiceRepository.class);
        invoiceService = new InvoiceService(invoiceRepositoryMock, mock(EntityManager.class));
    }

    @Test
    public void givenBreweryId_whenRetrieveBrewery_thenReturnBrewery() {
        when(invoiceRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(AN_INVOICE));

        Invoice brewery = invoiceService.retrieveInvoice(EXISTING_ID);

        assertEquals(AN_INVOICE, brewery);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenUnknownBreweryId_whenRetrieveBrewery_thenThowEntityNotFound() {
        when(invoiceRepositoryMock.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

        invoiceService.retrieveInvoice(UNKNOWN_ID);
    }

    @Test
    public void givenInvoice_whenCreateInvoice_thenTotalIsCalculated() {
        when(invoiceRepositoryMock.save(AN_INVOICE)).thenReturn(AN_INVOICE);

        Invoice invoice = invoiceService.createInvoice(AN_INVOICE);

        assertNotEquals(null, invoice.getTotal());
    }
}