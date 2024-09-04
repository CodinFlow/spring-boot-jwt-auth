package com.tericcabrel.authapi.services.serviceInterface;

import com.tericcabrel.authapi.entities.invoice.Invoice;

import java.util.List;
import java.util.Optional;


public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    List<Invoice> getNonArchivedInvoices();
    void deleteInvoiceById(int id);
    void archiveInvoiceById(int id);
    Optional<Invoice> getInvoiceById(int id);
}
