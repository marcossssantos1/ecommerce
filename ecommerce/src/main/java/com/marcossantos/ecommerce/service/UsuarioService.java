package com.marcossantos.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario criar(Usuario usuario) {
		return repository.save(usuario);
	}

}
