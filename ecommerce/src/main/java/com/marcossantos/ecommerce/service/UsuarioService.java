package com.marcossantos.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.dto.UsuarioCreateRequest;
import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario criar(UsuarioCreateRequest request) {
		Usuario usuario = Usuario.from(request);

		return repository.save(usuario);
	}

	public List<Usuario> listarTodosUsuarios() {
		return repository.findAll();
	}

	public Usuario listarUsuarioPorEmail(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado!!!"));
	}

}
