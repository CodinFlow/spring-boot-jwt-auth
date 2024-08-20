package com.tericcabrel.authapi.controllers.invoice;


import com.tericcabrel.authapi.entities.invoice.Invoice;
import com.tericcabrel.authapi.services.serviceImpl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable int id) {
        invoiceService.removeInvoice(id);

        return "Invoice deleted!";
    }

}
