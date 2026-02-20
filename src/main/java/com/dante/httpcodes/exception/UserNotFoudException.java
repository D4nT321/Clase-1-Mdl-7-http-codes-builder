package com.dante.httpcodes.exception;

public class UserNotFoudException extends RuntimeException {
    
    public UserNotFoudException() {
        super("El usuario no ha sido encontrado en el sistema");
    }
    
     public UserNotFoudException(String message) {
         super(message);
        
    }
}
