package com.marcossantos.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcossantos.ecommerce.dto.UsuarioCreateRequest;
import com.marcossantos.ecommerce.dto.UsuarioResponse;
import com.marcossantos.ecommerce.dto.UsuarioUpdateRequest;
import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping("/criar")
	public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioCreateRequest request) {

		Usuario usuario = service.criar(request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(location).body(usuario.toResponse());
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> listarTodosUsuarios() {
		service.listarTodosUsuarios();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> listarUsuarioPorId(@PathVariable Long id) {
		Usuario usuario = service.listarUsuarioPorEmail(id);
		return ResponseEntity.ok(usuario.toResponse());
	}

	@PutMapping("/{id}/atualizar")
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
			@Valid @RequestBody UsuarioUpdateRequest request) {
		Usuario usuario =  service.atualizar(request, id);

		return ResponseEntity.ok(usuario.toResponse());
	}

	@DeleteMapping("/{id}/deletar")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
