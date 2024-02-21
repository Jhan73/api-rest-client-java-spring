package com.jhan.tutorial.model.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//estas clses son similares al entity pero son las que se usan para el controller
@Data
@Builder
public class ClienteDto implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaRegistro;
}
