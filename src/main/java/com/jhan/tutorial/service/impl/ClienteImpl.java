package com.jhan.tutorial.service.impl;

import com.jhan.tutorial.model.dao.ClienteDao;
import com.jhan.tutorial.model.entity.Cliente;
import com.jhan.tutorial.service.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteImpl implements ClienteInterface {
    @Autowired
    private ClienteDao clienteDao;
    @Transactional //para manejar cietas excepciones por defecto para que no caiga la app
    @Override
    public Cliente save(Cliente cliente) {
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
}
