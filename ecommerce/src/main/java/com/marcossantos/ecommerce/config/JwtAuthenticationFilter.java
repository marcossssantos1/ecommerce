package com.marcossantos.ecommerce.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UsuarioRepository repository;

	public JwtAuthenticationFilter(JwtService jwtService, UsuarioRepository repository) {
		this.jwtService = jwtService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {

			String token = header.substring(7);
			String email = jwtService.getEmail(token);

			Usuario usuario = repository.findByEmail(email).orElse(null);

			if (usuario != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null,
						List.of());

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	}
}
