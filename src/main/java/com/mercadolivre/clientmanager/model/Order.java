package com.mercadolivre.clientmanager.model;

import com.mercadolivre.clientmanager.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Entity {
    private Integer id;
    private List<Product> products;
    private Double total;
    private Client client;
    private Date date;
}
