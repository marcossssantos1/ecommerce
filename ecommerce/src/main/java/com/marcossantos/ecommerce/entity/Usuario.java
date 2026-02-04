package com.marcossantos.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.marcossantos.ecommerce.dto.UsuarioCreateRequest;
import com.marcossantos.ecommerce.dto.UsuarioResponse;
import com.marcossantos.ecommerce.dto.UsuarioUpdateRequest;
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
	private TipoUsuario tipo = TipoUsuario.CLIENTE;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String cpf, String senha, String telefone, String cep,
			String logradouro, String numero, String complemento, String bairro, String cidade, String estado,
			LocalDateTime dataCadastro, Boolean ativo, TipoUsuario tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.telefone = telefone;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	public static Usuario from(UsuarioCreateRequest r) {
		Usuario u = new Usuario();

		u.nome = r.nome();
		u.email = r.email();
		u.cpf = r.cpf();
		u.senha = r.senha();
		u.telefone = r.telefone();

		u.cep = r.cep();
		u.logradouro = r.logradouro();
		u.numero = r.numero();
		u.complemento = r.complemento();
		u.bairro = r.bairro();
		u.cidade = r.cidade();
		u.estado = r.estado();

		u.tipo = TipoUsuario.CLIENTE;
		u.ativo = true;

		return u;
	}

	public UsuarioResponse toResponse() {
		return new UsuarioResponse(this.nome, this.email, this.cpf, this.telefone, this.cep, this.logradouro,
				this.numero, this.complemento, this.bairro, this.cidade, this.estado);
	}
	
	public void atualizar(UsuarioUpdateRequest r) {
	    this.nome = r.nome();
	    this.telefone = r.telefone();
	    this.cep = r.cep();
	    this.logradouro = r.logradouro();
	    this.numero = r.numero();
	    this.complemento = r.complemento();
	    this.bairro = r.bairro();
	    this.cidade = r.cidade();
	    this.estado = r.estado();
	}


}
