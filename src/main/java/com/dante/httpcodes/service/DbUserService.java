package com.dante.httpcodes.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dante.httpcodes.dto.UserDto;
import com.dante.httpcodes.dto.UserRequest;
import com.dante.httpcodes.exception.UserNotFoudException;
import com.dante.httpcodes.mapper.UserMapper;
import com.dante.httpcodes.repository.UserRepository;

@Primary //esta es una anotacion para diferenciarlos de los dos servicios
@Service
public class DbUserService implements UserService{
    

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public DbUserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper =  userMapper; 
        this.passwordEncoder = passwordEncoder; 
    }


    @Override
    public List<UserDto> getAll() {
      
        return userRepository.findAll().stream()
        .map(userMapper::toDto)
        .toList();
    }

    @Override
    public UserDto getByUsername(String username) {
        return userRepository.findById(username)
        .map(userMapper::toDto)
        .orElseThrow(() -> new UserNotFoudException());
    }

    @Override
    public UserDto getByEmail(String email) {
      return userRepository.findByEmail(email)
      .map(userMapper::toDto)
      .orElseThrow(() -> new UserNotFoudException());
    }

    @Override
    public UserDto create(UserRequest user) {
      var entity = userMapper.toEntity(user);
      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
      entity.setHirDate(LocalDate.now());
      entity.setActive(true);

      entity = userRepository.save(entity);
      
      return userMapper.toDto(entity);
      
    }

    @Override
    public Void delete(String username) {
       userRepository.deleteById(username);
       return null;
 
    }

    @Override
    public UserDto update(String username, UserRequest user) {
       var entity = userRepository.findById(username)
            .orElseThrow(() -> new UserNotFoudException());

            entity.setPassword(passwordEncoder.encode(user.getPassword()));
            entity.setPassword(user.getPassword());
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());

            entity = userRepository.save(entity);

            return userMapper.toDto(entity);

    

    }

    
}
