package com.dante.httpcodes.mapper;



import org.springframework.stereotype.Component;

import com.dante.httpcodes.dto.UserDto;
import com.dante.httpcodes.dto.UserRequest;
import com.dante.httpcodes.entity.UserEntity;
@Component
public class UserMapper {
    
    public UserDto toDto(UserEntity user) {


        var userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setHirDate(user.getHirDate());
        userDto.setActive(user.getActive());
        userDto.setRoles(user.getRoles());
        return userDto;

    }

    public UserEntity toEntity(UserRequest userDto) {


        var user = new UserEntity();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        userDto.setRoles(userDto.getRoles());
        return user;


    }




}
