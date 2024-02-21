package com.jhan.tutorial.model.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
public class Response implements Serializable {
    private String mensaje;
    private Object object;
    private HttpStatus status;
}
