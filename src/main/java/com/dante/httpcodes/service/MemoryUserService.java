package com.dante.httpcodes.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dante.httpcodes.dto.UserDto;
import com.dante.httpcodes.dto.UserRequest;
import com.dante.httpcodes.exception.AlreadyExistException;
import com.dante.httpcodes.exception.MissingValueException;
import com.dante.httpcodes.exception.UserNotFoudException;

@Service
public class MemoryUserService implements UserService {


    private List<UserDto> list = new ArrayList<>();


    @Override
    public List<UserDto> getAll() {
       
        return list;
    }

    @Override
    public UserDto getByUsername(String username) {
      return list.stream()
        .filter(u -> u.getUserName().equals(username))
        .findFirst()
        .orElseThrow(() -> new UserNotFoudException()); 
    }

    @Override
    public UserDto getByEmail(String email) {
       return list.stream()
        .filter(u -> u.getEmail().equals(email))
        .findFirst()
        .orElseThrow(() -> new UserNotFoudException(
            "No fue encontrado ningun usuario con el email dado")); 
    }

    @Override
    public UserDto create(UserRequest user) {
      
        //Validar que el username no exista en la lista
        if(user.getUserName()== null || user.getUserName().isBlank()) {
            throw new MissingValueException("El nombre de usuario no fue dado");
        }
        try {
              getByUsername(user.getUserName());
            throw new AlreadyExistException("Ya existe un usuario con el username dado");
        } catch (UserNotFoudException e) {
            //Esta bien 
        }

        
        //Validar que el email no exista en la lista

          if(user.getEmail()== null || user.getEmail().isBlank()) {
            throw new MissingValueException("El correo electronico no fue dado");
        }

        try {
             getByEmail(user.getEmail()); 
            throw new AlreadyExistException("Ya existe un usuario con el email dado");
        } catch (UserNotFoudException e) {
             //Esta bien 
       
        }

        var newUser = new UserDto();
        newUser.setName(user.getName());
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setHirDate(LocalDate.now());
        newUser.setActive(true);
       

        System.out.println(newUser);
        list.add(newUser);
        return newUser;
    }

    @Override
    public Void delete(String username) {
       var existing = getByUsername(username);

       list.remove(existing);
       return null;
       
    }
    
}
