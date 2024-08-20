package com.tericcabrel.authapi.repositories.invoice;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
}
