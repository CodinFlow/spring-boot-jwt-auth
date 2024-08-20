package com.tericcabrel.authapi.repositories.invoice;

import com.tericcabrel.authapi.entities.invoice.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
