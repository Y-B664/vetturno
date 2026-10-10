package com.veterinaria.vetturno.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> manejarErroresValidados(MethodArgumentNotValidException ex){

        Map<String, String> errores = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()){
            errores.put(fieldError.getField(),fieldError.getDefaultMessage());
            System.out.println(fieldError.getField());
        }
        Error error = new Error(ex.getStatusCode().value(),"Hay campos que no tienen valores validos",errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> manejarErrorCita(RuntimeException ex){
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),null );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> manejarErrorGlobal(Exception ex){
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),null );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }



}
