package com.marcossantos.ecommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcossantos.ecommerce.dto.UsuarioCreateRequest;
import com.marcossantos.ecommerce.dto.UsuarioResponse;
import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.service.UsuarioService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioCreateRequest request){
		
		Usuario usuario = service.criar(request);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuario.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(usuario.toResponse());
	}

}
