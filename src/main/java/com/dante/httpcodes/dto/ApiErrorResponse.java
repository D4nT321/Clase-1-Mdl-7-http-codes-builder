package com.dante.httpcodes.dto;


import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ApiErrorResponse<T> {
    
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private T message;
    private String path;

    public ApiErrorResponse(LocalDateTime timestamp, HttpStatus status, T message, String path) {
        this(timestamp, status.value(), status.getReasonPhrase(), message, path);
       
    }

    public ApiErrorResponse(LocalDateTime timestamp, Integer status, String error, T message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public T getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    
    
}
