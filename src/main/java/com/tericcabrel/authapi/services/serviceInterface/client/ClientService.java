package com.tericcabrel.authapi.services.serviceInterface.client;

import com.tericcabrel.authapi.entities.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);

    void updateClientById(int id, Client client);

    void deleteClientById(int id);

    Optional<Client> getClientById(int id);

    List<Client> getAllClients();
}
