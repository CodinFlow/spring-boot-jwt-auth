package com.tericcabrel.authapi.services.serviceImpl;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import com.tericcabrel.authapi.repositories.invoice.InvoiceRepository;
import com.tericcabrel.authapi.repositories.invoice.ItemRepository;
import com.tericcabrel.authapi.services.serviceInterface.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ItemRepository invoiceItemRepository;


    @Override
    public Invoice createInvoice(Invoice invoice) {
        // Set the invoice reference on each item
        invoice.getItems().forEach(item -> item.setInvoice(invoice));
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        Iterable<Invoice> invoiceIterable = invoiceRepository.findAll();
        List<Invoice> invoices = new ArrayList<>();
        invoiceIterable.forEach(invoices::add);
        return invoices;
    }

    @Override
    public void removeInvoice(int id) {
        invoiceRepository.deleteById(id);
    }
}
