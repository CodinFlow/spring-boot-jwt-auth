package com.tericcabrel.authapi.services.serviceImpl.client;


import com.tericcabrel.authapi.entities.client.Client;
import com.tericcabrel.authapi.repositories.client.ClientRepository;
import com.tericcabrel.authapi.services.serviceInterface.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void updateClientById(int id, Client client) {
        if(clientRepository.existsById(id)) {
            Client existingClient = clientRepository.findById(id).get();

            existingClient.setFirstname(client.getFirstname());
            existingClient.setLastname(client.getLastname());
            existingClient.setEmail(client.getEmail());
            existingClient.setPhoneNumber(client.getPhoneNumber());
            existingClient.setAddress(client.getAddress());


            clientRepository.save(existingClient);
        } else {
            throw new IllegalArgumentException("Client with id " + id + " does not exist");
        }

    }

    @Override
    public void deleteClientById(int id) {
        if(clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Client with id " + id + " does not exist");
        }

    }

    @Override
    public Optional<Client> getClientById(int id) {
        if (clientRepository.existsById(id)) {
            return clientRepository.findById(id);
        }else {
            throw new IllegalArgumentException("Client with id " + id + " does not exist");
        }
    }

    @Override
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }
}
