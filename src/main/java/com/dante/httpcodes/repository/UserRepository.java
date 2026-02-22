package com.dante.httpcodes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dante.httpcodes.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

Optional<UserEntity> findByEmail(String email);
    
    
}
