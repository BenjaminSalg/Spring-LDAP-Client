package com.example.springldapclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //Cualquier usuario
                        .requestMatchers("/", "/pagina_publica", "/error").permitAll()
                        //Solo usuarios autenticados
                        .requestMatchers("/panel").authenticated()
                        //Cualquier otra dirección es prohibida automáticamente
                        .anyRequest().denyAll()
                )
                .formLogin();
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                //Solo aquellos del grupo administradores
                .userDnPatterns("uid={0},ou=administradores")
                .contextSource()
                //IP y dominio del servidor LDAP
                .url("ldap://192.168.0.10:389/dc=redes2023,dc=com");
    }
}




