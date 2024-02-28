package com.jhan.tutorial.exception;

import com.jhan.tutorial.model.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

//Clase para controlar excepciones globalmente - para dar formato a las respuestas de excepciones al cliente
@RestControllerAdvice//clase que detectará todos los errores en el controller
public class GlobalExceptionHandler {
    //controla los errores de los campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException (MethodArgumentNotValidException exception, WebRequest webRequest){

        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapErrors.put(clave, valor);
                }
        );
        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    //controla los errores not found 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    //controla los errores de logica o de los catch en general 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handlerBadRequestException(BadRequestException exception,
                                                                  WebRequest webRequest) {
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    //controla los errores de varios tipos y globalizrlo con un error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerException(Exception exception,
                                                        WebRequest webRequest) {
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
