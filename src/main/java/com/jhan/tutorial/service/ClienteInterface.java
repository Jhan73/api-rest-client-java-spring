package com.jhan.tutorial.service;

import com.jhan.tutorial.model.dto.ClienteDto;
import com.jhan.tutorial.model.entity.Cliente;

public interface ClienteInterface {
    Cliente save(ClienteDto clienteDto);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
}
