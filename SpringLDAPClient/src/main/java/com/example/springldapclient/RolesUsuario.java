package com.example.springldapclient;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class RolesUsuario {
    private Collection<? extends GrantedAuthority> authorities;

    public RolesUsuario (Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    public boolean tieneRol(String rol) {
        return this.authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals(rol));
    }
}
