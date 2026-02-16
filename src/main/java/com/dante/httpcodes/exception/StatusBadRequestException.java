package com.dante.httpcodes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason = "El valor precesado no es correcto")
public class StatusBadRequestException extends RuntimeException {
    
}
