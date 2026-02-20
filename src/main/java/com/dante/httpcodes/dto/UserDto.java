package com.dante.httpcodes.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties({"active", "password"})
public class UserDto {
    
    //JsonAlias para mapear los campos userName y username del json al campo userName de la clase
    //@JsonAlias({"userName","username"})
    //JsonProperty para mapear el campo user_name del json al campo userName de la clase
    @JsonProperty(value="user_name", index = 0)
    
    private String userName;

    private String email;

    @JsonIgnore
    private String password;

    @JsonAlias({"nombre","fullname"})
    private String name;

    //@JsonIgnore 
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("hire_date")
    private LocalDate hirDate;

    private Boolean active;
  


    

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

    public LocalDate getHirDate() {
        return hirDate;
    }

    public void setHirDate(LocalDate hirDate) {
        this.hirDate = hirDate;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "UserDto [userName=" + userName + ", email=" + email + ", password=" + password + ", name=" + name
                + ", hireDate=" + hirDate + ",active=" + active + "]";
    }
   


    
}
