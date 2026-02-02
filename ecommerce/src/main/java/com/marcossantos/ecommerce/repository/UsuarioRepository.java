package com.marcossantos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcossantos.ecommerce.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
