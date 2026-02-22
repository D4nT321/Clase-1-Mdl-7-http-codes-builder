package com.dante.httpcodes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(opt -> opt.disable()))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/api/sample/**").permitAll() //401 sino las cumple
            .requestMatchers("/api/global/**").permitAll()
            .requestMatchers(HttpMethod.POST,"/api/users/**").hasRole("ADMIN") //.permitAll()//
            .requestMatchers(HttpMethod.PUT,"/api/users/**").hasAnyRole("ADMIN","USER")
            .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
            .anyRequest().authenticated())
            .httpBasic(httpBasic -> {});
        
         
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


   // @Bean
    //public UserDetailsService userDetailsService() {

        //PasswordEncoder passwordEncoder = es para encriptar la contraseña
        // https://bcrypt-generator.com/
        //var user = User.builder()
         //.username("user")
        // .password("$2a$12$d.HuO18t5tGeSpEWVYGVVOO0gxudGBSBVZCV5KC9DdmZ06DQS9GOi")
        // .roles("USER")
        // .build();

        // var admin = User.builder()
       //  .username("admin")
       //  .password("$2a$12$nPNMAxyKQCJk8Myoe0xeLuLd3EwegyPd8QVdNqHDQQS6DvKCbGVWC")
       //  .roles("USER", "ADMIN")
       //  .build();

      //   var guest = User.builder()
       //  .username("guest")
       //  .password("$2a$12$nPNMAxyKQCJk8Myoe0xeLuLd3EwegyPd8QVdNqHDQQS6DvKCbGVWC")
       //  .roles("GUEST")
       //  .build();

       //  return new InMemoryUserDetailsManager(user, admin, guest);

    // }

}
