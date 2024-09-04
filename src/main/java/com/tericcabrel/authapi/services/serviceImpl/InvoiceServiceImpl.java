package com.tericcabrel.authapi.services.serviceImpl;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import com.tericcabrel.authapi.repositories.invoice.InvoiceRepository;
import com.tericcabrel.authapi.repositories.invoice.ItemRepository;
import com.tericcabrel.authapi.services.serviceInterface.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ItemRepository invoiceItemRepository;


    @Override
    public Invoice createInvoice(Invoice invoice) {
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
    public List<Invoice> getNonArchivedInvoices() {
        return invoiceRepository.findByArchivedFalse();
    }

    @Override
    public void deleteInvoiceById(int id) {
        if(invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Invoice with id " + id + " does not exist");
        }

    }

    @Override
    public void archiveInvoiceById(int id) {
        if(invoiceRepository.existsById(id)){
            Invoice invoice = invoiceRepository.findById(id).get();
            invoice.setArchived(true);
            invoiceRepository.save(invoice);
        }else {
            throw new IllegalArgumentException("Invoice with id " + id + " does not exist");
        }
    }

    @Override
    public Optional<Invoice> getInvoiceById(int id) {
        if(invoiceRepository.existsById(id)){
            return invoiceRepository.findById(id);
        }else {
            return Optional.empty();
        }
    }
}
