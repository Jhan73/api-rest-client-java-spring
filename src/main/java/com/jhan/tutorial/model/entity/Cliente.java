package com.jhan.tutorial.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

//usando anotaciones de lombok
@Data //genera los getter, setter, hashCode, canEqualm, toString y equals.
      //pero tambien se puede decorar de manera individual como @setter, @getter ...
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor //constructor vacio
//@ToString //genera el toString
@Builder
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")//en este caso no es necesario este decorador ps en la base de datos tiene el mismo nombre el campo correspondiente al atributo Cliente
    private String nombre;
    private String apellido;
    private String correo;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
}
