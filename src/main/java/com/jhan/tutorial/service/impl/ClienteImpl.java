package com.jhan.tutorial.service.impl;

import com.jhan.tutorial.model.dao.ClienteDao;
import com.jhan.tutorial.model.dto.ClienteDto;
import com.jhan.tutorial.model.entity.Cliente;
import com.jhan.tutorial.service.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImpl implements ClienteInterface { //el deginición del tipo de retorno y parametro depende del caso de uso
    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> getAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Transactional //para manejar cietas excepciones por defecto para que no caiga la app
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .id(clienteDto.getId())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
        return clienteDao.save(cliente);
    }
    @Transactional(readOnly = true)//Generalmente se use @Transactional(readOnly = true) para la operacion de busqueda o recuperacion para
                                    //asegurarno de que solo podemos leer
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);//si no existe el cliente con el id retorna un null
    }
    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public boolean existById(Integer id) {
        return clienteDao.existsById(id);
    }
}
