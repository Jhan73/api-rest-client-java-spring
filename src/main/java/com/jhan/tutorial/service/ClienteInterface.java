package com.jhan.tutorial.service;

import com.jhan.tutorial.model.entity.Cliente;

public interface ClienteInterface {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
}
