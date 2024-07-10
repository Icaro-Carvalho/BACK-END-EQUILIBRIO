package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.GestorEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImplService implements UserDetails {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplService(Long id, String nomeCompleto, String email, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UserDetailsImplService build(GestorEntity gestorEntity) {
        return new UserDetailsImplService(gestorEntity.getId(), gestorEntity.getNomeCompleto(), gestorEntity.getEmail(), gestorEntity.getSenha(), new ArrayList<>());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
