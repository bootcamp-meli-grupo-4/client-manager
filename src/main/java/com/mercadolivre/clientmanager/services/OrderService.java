package com.mercadolivre.clientmanager.services;

import com.mercadolivre.clientmanager.model.Order;
import com.mercadolivre.clientmanager.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Integer save(Order order) {
        return orderRepository.save(order);
    }

    public Order find(Integer id) {
        return orderRepository.find(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    public void update(Order order) {
        orderRepository.update(order);
    }
}
