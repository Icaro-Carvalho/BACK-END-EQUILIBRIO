package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.GestorEntity;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private GestorRepository gestorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GestorEntity gestorEntity = gestorRepository.findByEmail(email).get();
        return UserDetailsImplService.build(gestorEntity);
    }
}
