package com.dante.httpcodes.config;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dante.httpcodes.dto.ApiErrorResponse;
import com.dante.httpcodes.exception.AlreadyExistException;
import com.dante.httpcodes.exception.EmailAlreadyExistsException;
import com.dante.httpcodes.exception.MissingValueException;
import com.dante.httpcodes.exception.UserNotFoudException;

import jakarta.servlet.http.HttpServletRequest;

//@RestControllerAdvice //Se usa cuando trabaja spring rest
@ControllerAdvice //Se usa cuando trabaja spring mvc
//aplica para todos los controladores 

public class GlobalExceptionHandler {

    //El globalException es una forma mas facil de mostrar las excepciones



     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse<List<String>>> handleMethodArgumentNotValidException(
       MethodArgumentNotValidException exception,
        HttpServletRequest request) {


             var message = exception.getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage()) // -:- es un separador de campos para mostrar en el mensaje
                .toList();

                
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse<List<String>> (
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST,
                       message,
                        request.getRequestURI()));
                
    }



    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleAlreadyExistExceptionException(
        AlreadyExistException exception,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse<String>(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT,
                        exception.getMessage(),
                        request.getRequestURI()));
                
    }


     @ExceptionHandler(MissingValueException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleMissingValueExceptionException(
        MissingValueException exception,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse<String>(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        request.getRequestURI()));
                
    }


      @ExceptionHandler(UserNotFoudException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleUserNotFoundException(
        UserNotFoudException exception,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse<String>(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        request.getRequestURI()));
                
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleEmailAlreadyExistException(
        EmailAlreadyExistsException exception,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse<String>(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST,
                        "El correo electronico ya existe en la lista de usuarios",
                        request.getRequestURI()));       
    }



    //Aqui vamos a definir que tipo de mensaje vamos a devolver


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse<String>> handleException(
        Exception exception,
        HttpServletRequest request) {
       return ResponseEntity.internalServerError()
               .body(new ApiErrorResponse<String>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
               ));
    }

}
