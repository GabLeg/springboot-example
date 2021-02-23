package com.example.springbootexample.controllers;

import com.example.springbootexample.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InvoiceControllerTest extends IntegrationTestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;

    @Test
    void givenInvoiceId_whenGetInvoice_thenReturn200() throws Exception {
        this.mockMvc.perform(get("/invoices/" + EXISTING_ID))
                    .andExpect(status().isOk());
    }

    @Test
    void givenUnknownInvoiceId_whenGetInvoice_thenReturn404() throws Exception {
        this.mockMvc.perform(get("/invoices/" + UNKNOWN_ID))
                    .andExpect(status().isNotFound());
    }

    @Test
    void givenNewInvoice_whenCreateInvoice_thenReturn201() throws Exception {
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
