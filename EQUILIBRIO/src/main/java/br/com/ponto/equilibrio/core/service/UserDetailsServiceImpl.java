package br.com.ponto.equilibrio.core.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.Rh;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final RhService rhService;

    public UserDetailsServiceImpl(RhService rhService) {
        this.rhService = rhService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Rh> usuario = rhService.encontrarPorEmail(email);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }

        Rh rh = usuario.get();
        return User.builder()
                .username(rh.getEmail())
                .password(rh.getSenha()) 
                .roles("USER")
                .build();
    }
    
}
