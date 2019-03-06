package com.example.springbootexample.controllers;

import com.example.springbootexample.controllers.dto.InvoiceDTO;
import com.example.springbootexample.domain.invoice.Invoice;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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
    public InvoiceDTO getInvoice(@PathVariable("id") @NotNull Long id) {
        return mapper.map(invoiceService.retrieveInvoice(id), InvoiceDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice invoice = mapper.map(invoiceDTO, Invoice.class);
        invoice = invoiceService.createInvoice(invoice);
        return mapper.map(invoice, InvoiceDTO.class);
    }
}
