package com.tericcabrel.authapi.controllers.client;
import com.tericcabrel.authapi.entities.client.Client;
import com.tericcabrel.authapi.services.serviceImpl.client.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;


    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        if (clientService.createClient(client) != null) {
            return ResponseEntity.ok("Client created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
       if(clientService.getClientById(id).isPresent()) {
           return ResponseEntity.ok(clientService.getClientById(id).get());
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable int id, @RequestBody Client client) {
       if(clientService.getClientById(id).isPresent()) {
           clientService.updateClientById(id, client);
           return ResponseEntity.ok("Client updated successfully");
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
       if(clientService.getClientById(id).isPresent()) {
           clientService.deleteClientById(id);
           return ResponseEntity.ok("Client deleted successfully");
       } else {
           return ResponseEntity.notFound().build();
       }
    }


}
