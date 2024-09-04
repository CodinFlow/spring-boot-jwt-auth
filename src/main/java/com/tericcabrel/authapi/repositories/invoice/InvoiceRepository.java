package com.tericcabrel.authapi.repositories.invoice;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    List<Invoice> findByArchivedFalse();
}
