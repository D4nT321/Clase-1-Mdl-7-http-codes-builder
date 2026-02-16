package com.dante.httpcodes.config;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dante.httpcodes.dto.ApiErrorResponse;
import com.dante.httpcodes.exception.EmailAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

//@RestControllerAdvice //Se usa cuando trabaja spring rest
@ControllerAdvice //Se usa cuando trabaja spring mvc
//aplica para todos los controladores 

public class GlobalExceptionHandler {

    //El globalException es una forma mas facil de mostrar las excepciones
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExistException(
        EmailAlreadyExistsException exception,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST,
                        "El correo electronico ya existe en la lista de usuarios",
                        request.getRequestURI()));
                    


                
    }



    //Aqui vamos a definir que tipo de mensaje vamos a devolver


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(
        Exception exception,
        HttpServletRequest request) {
       return ResponseEntity.internalServerError()
               .body(new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
               ));
    }

}
