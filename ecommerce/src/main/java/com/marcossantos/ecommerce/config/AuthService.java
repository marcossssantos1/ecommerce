package com.marcossantos.ecommerce.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.dto.LoginRequest;
import com.marcossantos.ecommerce.dto.LoginResponse;
import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.exception.UsuarioException;
import com.marcossantos.ecommerce.repository.UsuarioRepository;

@Service
public class AuthService {

	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UsuarioRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public LoginResponse login(LoginRequest request) {

		Usuario usuario = repository.findByEmail(request.email())
				.orElseThrow(() -> new UsuarioException("Email ou senha inválidos"));

		if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
			throw new UsuarioException("Email ou senha inválidos");
		}

		String token = jwtService.gerarToken(usuario);

		return new LoginResponse(token, "Bearer");
	}
}
