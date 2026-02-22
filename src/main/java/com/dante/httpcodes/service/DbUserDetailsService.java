package com.dante.httpcodes.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dante.httpcodes.repository.UserRepository;

public class DbUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        var user = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

       // var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        var authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.toString()))
        .toList();

        return new User(user.getUserName(), user.getPassword(), authorities);
    }

    
}
