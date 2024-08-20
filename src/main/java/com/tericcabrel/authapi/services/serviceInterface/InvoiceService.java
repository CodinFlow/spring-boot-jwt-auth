package com.tericcabrel.authapi.services.serviceInterface;

import com.tericcabrel.authapi.entities.invoice.Invoice;

import java.util.List;


public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    void removeInvoice(int id);
}
