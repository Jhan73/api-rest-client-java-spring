package com.jhan.tutorial.controller;

import com.jhan.tutorial.model.entity.Cliente;
import com.jhan.tutorial.service.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    /*@DeleteMapping("/cliente/{id}")//{id} debe coincidir con el nombre del parametro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteInterface.findById(id);
        clienteInterface.delete(clienteDelete);
    }*/
    @DeleteMapping("/cliente/{id}")//{id} debe coincidir con el nombre del parametro
    //? significa cualquier objeto
    public ResponseEntity<?> delete(@PathVariable Integer id){//ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado
                                                                    //permitiendo total libertad de configurar las respuesta que se quiere enviar por los endpoints
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteInterface.findById(id);
            clienteInterface.delete(clienteDelete);

            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e){
            response.put("mensaje", e.getMessage());
            response.put("Cliente", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente showById(@PathVariable Integer id){
        return clienteInterface.findById(id);
    }


}
