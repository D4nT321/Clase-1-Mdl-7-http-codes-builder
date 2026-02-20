package com.dante.httpcodes.service;

import java.util.List;

import com.dante.httpcodes.dto.UserDto;
import com.dante.httpcodes.dto.UserRequest;

public interface UserService {

    

    List<UserDto> getAll();

    UserDto getByUsername(String username);

    UserDto getByEmail(String email);

    UserDto create (UserRequest user);

    Void delete(String username);


    
}
