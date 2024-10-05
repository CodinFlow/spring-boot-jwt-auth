package com.tericcabrel.authapi.repositories.invoice;

import com.tericcabrel.authapi.entities.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    List <Invoice> findByUsernameAndArchivedFalse(String username);
}
