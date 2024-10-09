package com.tericcabrel.authapi.repositories.client;

import com.tericcabrel.authapi.entities.client.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
