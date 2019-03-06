package com.example.integration.controllers;

import com.example.integration.config.IntegrationTestParent;
import com.example.springbootexample.controllers.InvoiceController;
import com.example.springbootexample.domain.invoice.Invoice;
import com.example.springbootexample.services.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = InvoiceController.class)
public class InvoiceControllerTest extends IntegrationTestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;
    private static final Invoice AN_INVOICE = new Invoice();

    @MockBean
    private InvoiceService invoiceServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenInvoiceId_whenGetInvoice_thenReturn200() throws Exception {
        when(invoiceServiceMock.retrieveInvoice(EXISTING_ID)).thenReturn(AN_INVOICE);
        this.mockMvc.perform(get("/invoices/" + EXISTING_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUnknownInvoiceId_whenGetInvoice_thenReturn404() throws Exception {
        when(invoiceServiceMock.retrieveInvoice(UNKNOWN_ID)).thenThrow(EntityNotFoundException.class);
        this.mockMvc.perform(get("/invoices/" + UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNewInvoice_whenCreateInvoice_thenReturn201() throws Exception {
        when(invoiceServiceMock.createInvoice(AN_INVOICE)).thenReturn(AN_INVOICE);
        this.mockMvc.perform(post("/invoices")
                            .content(objectMapper.writeValueAsString(AN_INVOICE))
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
