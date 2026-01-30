package com.marcossantos.ecommerce.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcossantos.ecommerce.entity.Usuario;

public class UsuarioDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Usuario usuario;

	public UsuarioDetails(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getTipo().name()));
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !usuario.estaBloqueado();
	}

	@Override
	public boolean isEnabled() {
		return usuario.getAtivo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
