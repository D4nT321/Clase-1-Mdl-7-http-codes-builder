package com.dante.httpcodes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dante.httpcodes.exception.EmailAlreadyExistsException;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/global")
public class GlobalController {
    
    private List<Map<String, Object>> list = new ArrayList<>();
   
    @GetMapping
    public List<Map<String, Object>> getAll () {
        return list;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createandReturnIndex(@RequestBody Map<String, Object>entity) {
        
        var email = entity.get("email");
        if (isEmailInList(email)) {
            throw new EmailAlreadyExistsException();
        }   
        
        
        var size = list.size();
        list.add(entity);
        return size;
        
    }

    private boolean isEmailInList(Object email) {
        //Contar  las veces que se encuentra el email en la lista
        var count = list.stream()   
        .filter(o -> o.get("email").equals(email))
        .count();

        //Si el contador es 0, no se encuentra, si es 1, si se encuentra en la lista 
        return count == 1;
    }
    
}
