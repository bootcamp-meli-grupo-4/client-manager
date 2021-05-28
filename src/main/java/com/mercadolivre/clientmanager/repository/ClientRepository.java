package com.mercadolivre.clientmanager.repository;

import com.mercadolivre.clientmanager.model.Client;
import com.mercadolivre.clientmanager.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository extends BaseRepository<Client> {
    public List<Order> getAllOrders(Integer id) {
        Client client = this.find(id);
        return client.getOrders();
    }
}
