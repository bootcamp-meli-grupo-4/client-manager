package com.mercadolivre.clientmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Entity{
    private Integer id;
    private String description;
    private String color;
    private Integer quantity;
    private Double price;
}
