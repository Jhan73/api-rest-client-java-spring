package com.jhan.tutorial.model.dao;

import com.jhan.tutorial.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {//se extiende pasandole la clase , el tipo de su identificador "id"
                                                                        // esto es para hacer querymetods (consultas CRUD basicas)

}
