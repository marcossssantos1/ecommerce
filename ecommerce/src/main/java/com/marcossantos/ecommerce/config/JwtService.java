package com.marcossantos.ecommerce.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "chave-super-secreta-com-32-bytes-no-minimo";
	private static final long EXPIRACAO = 1000 * 60 * 60; // 1h

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String gerarToken(Usuario usuario) {
        return Jwts.builder()
            .setSubject(usuario.getEmail())
            .claim("role", usuario.getTipo().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

	public String extrairEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean tokenValido(String token) {
		try {
			extrairEmail(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
