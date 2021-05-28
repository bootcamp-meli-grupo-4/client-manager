package com.mercadolivre.clientmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Entity {
    private Integer id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private List<Order> orders;
}
