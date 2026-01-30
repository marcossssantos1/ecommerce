package com.marcossantos.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
		Usuario usuarios = service.criar(usuario);
		return ResponseEntity.created(null).body(usuarios);
	}

}
