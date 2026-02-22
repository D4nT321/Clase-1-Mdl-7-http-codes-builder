package com.dante.httpcodes.dto;

import java.util.Set;

import com.dante.httpcodes.entity.Role;
import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class UserRequest {
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
     //JsonAlias para mapear los campos userName y username del json al campo userName de la clase
    @JsonAlias({"userName","username"})
    //JsonProperty para mapear el campo user_name del json al campo userName de la clase
    @JsonProperty(value="user_name", index = 0)
    private String userName;

    @NotBlank(message = "El correo electronico es obligatorio")
    @Email(message = "El formato de correo no es valido")
    private String email;

   
    @NotBlank(message = "La contraseña es obligatorio")
    private String password;

    @JsonAlias({"nombre","fullname"})
    private String name;

    @NotEmpty(message = "Los roles son obligatorios")
    protected Set<Role> roles;


    // Getters y setters para cada campo.
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

   

    
}
