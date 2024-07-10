package br.com.ponto.equilibrio.api.security.jwt;

import br.com.ponto.equilibrio.core.service.UserDetailsImplService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.util.Date;

@Component
@Log4j2
public class JwtUtils {

    @Value("${equilibrio.jwtSecret}")
    private String jwtSecret;

    @Value("${equilibrio.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImplService userDetailsImpl) {
        return Jwts.builder().setSubject(userDetailsImpl.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getLoginToken(String token) {
        return Jwts.parser().setSigningKey(getSigninKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.warn("Token inválido {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("Token expirado {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("Token não suportado {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("Token Argumento inválido {}", e.getMessage());
        }
        return false;
    }
}
