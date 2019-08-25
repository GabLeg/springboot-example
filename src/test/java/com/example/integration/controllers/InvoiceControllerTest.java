package com.example.integration.controllers;

import com.example.integration.config.IntegrationTestParent;
import com.example.springbootexample.domain.invoice.Invoice;
import com.example.springbootexample.services.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InvoiceControllerTest extends IntegrationTestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;

    @Test
    public void givenInvoiceId_whenGetInvoice_thenReturn200() throws Exception {
        this.mockMvc.perform(get("/invoices/" + EXISTING_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUnknownInvoiceId_whenGetInvoice_thenReturn404() throws Exception {
        this.mockMvc.perform(get("/invoices/" + UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNewInvoice_whenCreateInvoice_thenReturn201() throws Exception {
        String newInvoice = "{" +
                "\"purchaseItemList\":[" +
                "   {" +
                "       \"quantity\":2," +
                "       \"beer\": {" +
                "           \"id\":1" +
                "       }" +
                "   }," +
                "   {" +
                "       \"quantity\":3," +
                "       \"beer\": {" +
                "           \"id\":1" +
                "       }" +
                "   }" +
                "]" +
                "}";
        this.mockMvc.perform(post("/invoices")
                            .content(newInvoice)
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
