package com.jhan.tutorial.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//estas clses son similares al entity pero son las que se usan para el controller
@Data
@Builder
public class ClienteDto implements Serializable {
    private int id;
    @Size(min = 2, max = 25)
    @NotEmpty(message = "Nombre requerido")
    private String nombre;
    @Size(min = 2, max = 25)
    private String apellido;
    @Email
    @NotEmpty(message = "Apellido requerido")
    private String correo;
    private Date fechaRegistro;
}
