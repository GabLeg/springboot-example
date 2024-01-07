package com.example.springbootexample.domain.services;

import com.example.springbootexample.domain.object.invoice.Invoice;
import com.example.springbootexample.domain.object.invoice.PurchaseItem;
import com.example.springbootexample.infra.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final EntityManager entityManager;

  @Autowired
  public InvoiceService(InvoiceRepository invoiceRepository, EntityManager entityManager) {
    this.invoiceRepository = invoiceRepository;
    this.entityManager = entityManager;
  }

  public Invoice retrieveInvoice(Long id) {
    Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
    if (optionalInvoice.isPresent()) {
      return optionalInvoice.get();
    }
    throw new EntityNotFoundException();
  }

  @Transactional
  public Invoice createInvoice(Invoice invoice) {
    for (PurchaseItem purchaseItem : invoice.getPurchaseItemList()) {
      purchaseItem.setInvoice(invoice);
    }
    invoice = invoiceRepository.save(invoice);
    entityManager.refresh(invoice); // that way, beer properties are filled with DB infos
    invoice.calculateTotal();
    invoice.setTransactionDate(LocalDateTime.now());
    return invoiceRepository.save(invoice);
  }
}
