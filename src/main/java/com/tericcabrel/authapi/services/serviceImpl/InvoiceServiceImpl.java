package com.tericcabrel.authapi.services.serviceImpl;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import com.tericcabrel.authapi.entities.invoice.Status;
import com.tericcabrel.authapi.repositories.invoice.InvoiceRepository;
import com.tericcabrel.authapi.repositories.invoice.ItemRepository;
import com.tericcabrel.authapi.services.serviceInterface.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);


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
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invoice with id " + id + " does not exist");
        }

    }

    @Override
    public void archiveInvoiceById(int id) {
        if (invoiceRepository.existsById(id)) {
            Invoice invoice = invoiceRepository.findById(id).get();
            invoice.setArchived(true);
            invoiceRepository.save(invoice);
        } else {
            throw new IllegalArgumentException("Invoice with id " + id + " does not exist");
        }
    }

    @Override
    public void markAsPaid(int id) {
        if (invoiceRepository.existsById(id)) {
            Invoice invoice = invoiceRepository.findById(id).get();
            invoice.setStatus(Status.PAYED);
            invoiceRepository.save(invoice);
        } else {
            throw new IllegalArgumentException("Invoice with id " + id + " does not exist");
        }
    }

    @Override
    public Optional<Invoice> getInvoiceById(int id) {
        if (invoiceRepository.existsById(id)) {
            return invoiceRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void updateInvoice(int id, Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice with id " + id + " does not exist"));
        if (existingInvoice.getStatus() == Status.PAYED) {
            throw new IllegalArgumentException("Invoice with id " + id + " has been paid and cannot be updated");
        }

        invoice.setId(id);
        invoice.getItems().forEach(item -> item.setInvoice(invoice));
        invoiceRepository.save(invoice);


    }
}
