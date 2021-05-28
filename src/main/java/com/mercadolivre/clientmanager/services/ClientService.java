package com.mercadolivre.clientmanager.services;

import com.mercadolivre.clientmanager.model.Client;
import com.mercadolivre.clientmanager.model.Order;
import com.mercadolivre.clientmanager.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Order> getAllOrders(Integer id) {
        return clientRepository.getAllOrders(id);
    }

    public Integer save(Client client) {
        return clientRepository.save(client);
    }

    public Client find(Integer id) {
        return clientRepository.find(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void delete(Integer id) {
    }

    public void update(Client client) {
    }
}