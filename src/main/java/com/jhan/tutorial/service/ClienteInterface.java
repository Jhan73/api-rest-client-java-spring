package com.jhan.tutorial.service;

import com.jhan.tutorial.model.dto.ClienteDto;
import com.jhan.tutorial.model.entity.Cliente;

import java.util.List;

public interface ClienteInterface {
    List<Cliente> getAll();
    Cliente save(ClienteDto clienteDto);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    boolean existById(Integer id);
}
