package com.tericcabrel.authapi.repositories.client;

import com.tericcabrel.authapi.entities.client.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
