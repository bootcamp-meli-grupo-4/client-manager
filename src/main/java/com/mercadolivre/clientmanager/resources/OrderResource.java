package com.mercadolivre.clientmanager.resources;

import com.mercadolivre.clientmanager.model.Order;
import com.mercadolivre.clientmanager.model.Order;
import com.mercadolivre.clientmanager.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderResource {

    OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> allOrders(){
        List<Order> all = orderService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id){
        Order order = orderService.find(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody Order order){
        Integer id = orderService.save(order);
        URI uri = URI.create(String.format("/order/%d", id));
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateOrder(@RequestBody Order order){
        orderService.update(order);
        return ResponseEntity.ok().build();
    }
}
