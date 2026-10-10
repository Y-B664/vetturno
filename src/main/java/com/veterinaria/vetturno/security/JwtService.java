package com.veterinaria.vetturno.security;

import com.veterinaria.vetturno.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {


    private final SecretKey clave;
    private final long expiracion = 60*60*1000;

    public JwtService(@Value("${jwt.secretKey}") String secretKey) {
        this.clave = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generarToken(Usuario usuario){
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + expiracion);
        return Jwts.builder()
                .signWith(clave)
                .expiration(expira)
                .subject(usuario.getEmail())
                .claim("Rol",usuario.getRol())
                .issuedAt(ahora)
                .compact();
    }
    public String obtenerEmail(String token){
        return Jwts.parser()
                .verifyWith(clave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean esValido(String token) {
        try {
            Jwts.parser()
                    .verifyWith(clave)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
