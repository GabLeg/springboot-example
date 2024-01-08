package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.api.controllers.dto.jpa.InvoiceDto;
import com.example.springbootexample.domain.object.invoice.Invoice;
import com.example.springbootexample.domain.services.InvoiceService;
import com.example.springbootexample.mappers.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  private final InvoiceService invoiceService;
  private final ModelMapper mapper;

  @Autowired
  public InvoiceController(InvoiceService invoiceService, ModelMapper mapper) {
    this.invoiceService = invoiceService;
    this.mapper = mapper;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public InvoiceDto getInvoice(@PathVariable("id") @NonNull Long id) {
    return mapper.map(invoiceService.retrieveInvoice(id), InvoiceDto.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDTO) {
    Invoice invoice = mapper.map(invoiceDTO, Invoice.class);
    invoice = invoiceService.createInvoice(invoice);
    return mapper.map(invoice, InvoiceDto.class);
  }
}
