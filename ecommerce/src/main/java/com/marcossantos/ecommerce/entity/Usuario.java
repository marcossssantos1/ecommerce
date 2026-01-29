package com.marcossantos.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios", indexes = {
		@Index(name = "idx_email", columnList = "email"),
		@Index(name = "idx_cpf", columnList = "cpf")
		})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 200)
	@Column(nullable = false, length = 200)
	private String nome;
	
	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail é obrigatório")
	@Column(nullable = false, unique = true, length = 200)
	private String email;
	
	@Pattern(regexp = "\\d{11}", message =  "CPF deve conter 11 dígitos")
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@NotBlank(message = "Senha é obrigatório")
	@Column(nullable = false)
	private String senha;
	
	@Pattern(regexp = "\\d{10,11}", message =  "Telefone inválido")
	@Column(length = 15)
	private String telefone;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	@Column(nullable = false)
	private Boolean ativo = true;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoUsuario tipo = TipoUsuario.CLIENTE;
	
	@Column(name = "tentativas_login")
	private Integer tentativasLogin = 0;
	
	@Column(name = "bloqueado_ate")
	private LocalDateTime bloqueadoAte;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Carrinho carrinho;
	
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<>();
	
	

}
