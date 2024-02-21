package com.jhan.tutorial.controller;

import com.jhan.tutorial.model.entity.Cliente;
import com.jhan.tutorial.service.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")//Se utiliza para asignar solicitudes web a clases de controlador y/o metodos de controlador
public class ClienteController {
    @Autowired
    private ClienteInterface clienteInterface;
    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente){
        return clienteInterface.save(cliente);
    }
    @PutMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente){
        return clienteInterface.save(cliente);
    }
    @DeleteMapping("/cliente/{id}")//{id} debe coincidir con el nombre del parametro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteInterface.findById(id);
        clienteInterface.delete(clienteDelete);
    }
    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente showById(@PathVariable Integer id){
        return clienteInterface.findById(id);
    }


}
