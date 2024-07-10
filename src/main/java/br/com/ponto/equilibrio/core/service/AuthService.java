package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.security.jwt.JwtUtils;
import br.com.ponto.equilibrio.api.vo.AcessVO;
import br.com.ponto.equilibrio.api.vo.AuthenticationVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public AcessVO login(AuthenticationVO authenticationVO) {
        
        try {
            //Cria mecanismo de credencial para o spring
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authenticationVO.getEmail(), authenticationVO.getSenha());
            //Prepara mecanismo para autenticacao
            Authentication authentication = authenticationManager.authenticate(userAuth);
            //Busca usuario logado
            UserDetailsImplService userAuthenticate = (UserDetailsImplService) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
            AcessVO acessVO = new AcessVO(token);
            return acessVO;
        }catch (BadCredentialsException e) {
            log.warn("Login ou senha invalido");
        }
        return new AcessVO("Acesso Negado");
    }
}
