package com.mercadolivre.clientmanager.resources;

import com.mercadolivre.clientmanager.model.Client;
import com.mercadolivre.clientmanager.model.Order;
import com.mercadolivre.clientmanager.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    ClientService clientService;

    public ClientResource(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping(path = "/{id}/client")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Integer id){
        List<Order> orders= clientService.getAllOrders(id);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping
    public ResponseEntity<List<Client>> allClients(){
        List<Client> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id){
        Client client = clientService.find(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<Void> addClient(@RequestBody Client client){
        Integer id = clientService.save(client);
        URI uri = URI.create(String.format("/client/%d", id));
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateClient(@RequestBody Client client){
        clientService.update(client);
        return ResponseEntity.ok().build();
    }
}
