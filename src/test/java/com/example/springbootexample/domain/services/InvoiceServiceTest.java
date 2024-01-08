package com.example.springbootexample.domain.services;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.object.invoice.Invoice;
import com.example.springbootexample.infra.repository.InvoiceRepository;
import com.example.springbootexample.testUtils.InvoiceCreator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

class InvoiceServiceTest extends TestParent {

  private static final Long EXISTING_ID = 1L;
  private static final Long UNKNOWN_ID = 2L;
  private static final Invoice AN_INVOICE = InvoiceCreator.create();

  @Mock
  private InvoiceRepository invoiceRepositoryMock;
  @Mock
  private EntityManager entityManager;

  private InvoiceService invoiceService;

  @BeforeEach
  void init() {
    invoiceService = new InvoiceService(invoiceRepositoryMock, entityManager);
  }

  @Test
  void givenBreweryId_whenRetrieveBrewery_thenReturnBrewery() {
    when(invoiceRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(AN_INVOICE));

    Invoice brewery = invoiceService.retrieveInvoice(EXISTING_ID);

    assertThat(brewery).isEqualTo(AN_INVOICE);
  }

  @Test
  void givenUnknownBreweryId_whenRetrieveBrewery_thenThowEntityNotFound() {
    when(invoiceRepositoryMock.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

    Assertions.assertThrows(EntityNotFoundException.class, () -> invoiceService.retrieveInvoice(UNKNOWN_ID));
  }

  @Test
  void givenInvoice_whenCreateInvoice_thenTotalIsCalculated() {
    when(invoiceRepositoryMock.save(AN_INVOICE)).thenReturn(AN_INVOICE);

    Invoice invoice = invoiceService.createInvoice(AN_INVOICE);

    assertThat(invoice.getTotal()).isNotNull();
  }
}
