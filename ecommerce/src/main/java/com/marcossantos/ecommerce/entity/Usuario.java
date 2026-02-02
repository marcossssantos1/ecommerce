package com.marcossantos.ecommerce.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.marcossantos.ecommerce.enums.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario", indexes = { @Index(name = "idx_email", columnList = "email"),
		@Index(name = "idx_cpf", columnList = "cpf"), @Index(name = "idx_ativo", columnList = "ativo"),
		@Index(name = "idx_tipo", columnList = "tipo") })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String nome;

	@Column(nullable = false, unique = true, length = 200)
	private String email;

	@Column(nullable = false, unique = true, length = 11)
	private String cpf;

	@Column(nullable = false)
	private String senha;

	private String telefone;

	@Column(nullable = false, length = 8)
	private String cep;

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String numero;

	private String complemento;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false, length = 2)
	private String estado;

	@CreationTimestamp
	@Column(name = "data_cadastro", updatable = false)
	private LocalDateTime dataCadastro;

	@Column(nullable = false)
	private Boolean ativo = true;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoUsuario tipo;
}
