package com.jhan.tutorial.controller;

import com.jhan.tutorial.exception.ResourceNotFoundException;
import com.jhan.tutorial.model.dto.ClienteDto;
import com.jhan.tutorial.model.entity.Cliente;
import com.jhan.tutorial.model.payload.Response;
import com.jhan.tutorial.service.ClienteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")//Se utiliza para asignar solicitudes web a clases de controlador y/o metodos de controlador
public class ClienteController {
    @Autowired
    private ClienteInterface clienteService;

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response> showAll(){
        List<Cliente> clientes = clienteService.getAll();

        if(clientes == null || clientes.isEmpty()){
            throw new ResourceNotFoundException("Clientes");
            /*return new ResponseEntity<>(Response.builder()
                    .mensaje("No hay registros de clientes")
                    .object(null)
                    .status(HttpStatus.NOT_FOUND)
                    .build()
                    , HttpStatus.NOT_FOUND
            );*/
        } else{
            return new ResponseEntity<>(Response.builder()
                    .mensaje("")
                    .object(clientes)
                    .status(HttpStatus.OK)
                    .build()
                    , HttpStatus.OK);
        }

    }
    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response> create(@RequestBody ClienteDto clienteDto){
        try {
            Cliente clienteSave = clienteService.save(clienteDto);
            clienteDto =  ClienteDto.builder()
                    .id(clienteSave.getId())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(Response.builder()
                    .mensaje("Cliente guardado correctamente")
                    .object(clienteDto)
                    .status(HttpStatus.CREATED)
                    .build()
                    , HttpStatus.CREATED
                    );
        } catch (DataAccessException e){
            return new ResponseEntity<>(Response.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @PutMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id){//se puede mejorar creando un metodo update en service

        try {
            if (clienteService.existById(id)){
                clienteDto.setId(id);
                Cliente clienteUpdate =  clienteService.save(clienteDto);
                clienteDto = ClienteDto.builder()
                        .id(clienteUpdate.getId())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(Response.builder()
                        .mensaje("Cliente actualizado correctamente")
                        .object(clienteDto)
                        .status(HttpStatus.OK)
                        .build()
                        , HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(Response.builder()
                        .mensaje("El cliente no existe")
                        .object(null)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                        , HttpStatus.NOT_FOUND
                );
            }


        } catch (DataAccessException e){
            return new ResponseEntity<>(Response.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    /*@DeleteMapping("/cliente/{id}")//{id} debe coincidir con el nombre del parametro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteInterface.findById(id);
        clienteInterface.delete(clienteDelete);
    }*/
    @DeleteMapping("/cliente/{id}")//{id} debe coincidir con el nombre del parametro
    //? significa cualquier objeto
    public ResponseEntity<Response> delete(@PathVariable Integer id){//ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado
                                                                    //permitiendo total libertad de configurar las respuesta que se quiere enviar por los endpoints
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);

            return new ResponseEntity<>(Response.builder()
                    .mensaje("Cliente eliminado correctamente")
                    .object(clienteDelete)
                    .status(HttpStatus.NO_CONTENT)
                    .build()
                    , HttpStatus.NO_CONTENT);
        } catch (DataAccessException e){
            return new ResponseEntity<>(Response.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        if(cliente == null){
            throw new ResourceNotFoundException("cliente", "id", id);
            /*return new ResponseEntity<>(Response.builder()
                    .mensaje("El cliente no existe")
                    .object(null)
                    .status(HttpStatus.NOT_FOUND)
                    .build()
                    , HttpStatus.NOT_FOUND
            );*/
        } else{
            ClienteDto clienteDto = ClienteDto.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .correo(cliente.getCorreo())
                    .fechaRegistro(cliente.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(Response.builder()
                    .mensaje("")
                    .object(clienteDto)
                    .status(HttpStatus.OK)
                    .build()
                    , HttpStatus.OK);
        }

    }


}
