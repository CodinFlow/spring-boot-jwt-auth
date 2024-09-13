package com.tericcabrel.authapi.controllers.invoice;


import com.tericcabrel.authapi.entities.invoice.Invoice;
import com.tericcabrel.authapi.services.serviceImpl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("invoices")
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

    @GetMapping("/non-archived")
    public ResponseEntity<List<Invoice>> getNonArchivedInvoices() {
        List<Invoice> invoices = invoiceService.getNonArchivedInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
   public ResponseEntity<Optional<Invoice>> getInvoiceById(@PathVariable int id) {
       if (invoiceService.getInvoiceById(id).isPresent()) {
           return ResponseEntity.ok(invoiceService.getInvoiceById(id));
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoiceById(id);
        return ResponseEntity.ok("Invoice with id " + id + " has been deleted");
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<String> archiveInvoice(@PathVariable int id) {
        try{
        invoiceService.archiveInvoiceById(id);
        return ResponseEntity.ok("Invoice with id " + id + " has been archived");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-as-paid")
    public ResponseEntity<String> markAsPaid(@PathVariable int id) {
        try{
        invoiceService.markAsPaid(id);
        return ResponseEntity.ok("Invoice with id " + id + " has been marked as paid");
        } catch (IllegalArgumentException e) {

            return e.getMessage().contains("Invoice with id") ? ResponseEntity.notFound().build() : ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable int id, @RequestBody Invoice invoice) {

        if (invoiceService.getInvoiceById(id).isPresent()) {
            invoice.setId(id);
            invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok("Invoice with id " + id + " has been updated");
        } else {
            return ResponseEntity.notFound().build();



        }
    }

}
