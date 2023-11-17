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
                .csrf((csrf) -> csrf.disable()) //Desactiva el CSRF
                .authorizeHttpRequests(authorize -> authorize
                        //Usuario no logeado
                        .requestMatchers("/login").anonymous()
                        //Cualquier usuario
                        .requestMatchers("/css/**", "/", "/inicio", "/error", "/logout").permitAll()
                        //Solo administradores
                        .requestMatchers("/panel").hasAnyRole("ADMIN-VENTAS", "ADMIN-CATALOGO")
                        //Cualquier otra dirección es prohibida automáticamente
                        .anyRequest().denyAll()
                )
                //Config del login y logout
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/inicio")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error")
                )
                .logout();
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                //Cualquier usuario puede iniciar sesion
                .userSearchFilter("(uid={0})")
                .contextSource()
                //IP y dominio del servidor LDAP
                .url("ldap://192.168.0.22:389/dc=redes2023,dc=com");
    }
}








