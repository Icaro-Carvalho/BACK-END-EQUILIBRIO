package br.com.ponto.equilibrio.api.security;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "6ce59c594ce48d535c5581575707dc6f140dfe571c309b03e0d0131c15a70621e450f38e4ef04de2f59c8edd7e590561ce90d2aacee67a7980eeb7cb9b691679f218d8478f3ea5e5df11d4f42fb5a30b6cb7758a5d27ab8aacf9d9d5f5c42caab8176f1e1b145baf056cae59a1e2aed4e5ad8eb92627a9a5666edfd239e1a27a7e0c9a0d369ed866aec91252ac4767f94ba3287473df54ef51b6b48193b4795fecf314234f42aee6d1b14a0e4bbed75fa65d3f02d018d2e98bdca7b068c0fa1f893804905b02ebd10aa50e354d99426237f51b04e1d972c39662daf84bab12804fc431cab5cb407d3b0b18aca679af3d267630e8046f492f87f7d5a2a5a7de6f";

    public String gerarToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        String email = extrairEmail(token);
        return (email.equals(userDetails.getUsername()) && !tokenExpirado(token));
    }

    public String extrairEmail(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public Date extrairDataExpiracao(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    private boolean tokenExpirado(String token) {
        return extrairDataExpiracao(token).before(new Date());
    }

    private <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);   
    }

    
}
