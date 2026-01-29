package com.marcossantos.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco", indexes = { @Index(name = "idx_usuario", columnList = "usuario_id"),
		@Index(name = "idx_cep", columnList = "cep") })
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_usuario"))
	private Usuario usuario;

	@Column(nullable = false, length = 8)
	private String cep;

	@Column(nullable = false, length = 255)
	private String logradouro;

	@Column(nullable = false, length = 20)
	private String numero;

	@Column(length = 100)
	private String complemento;

	@Column(nullable = false, length = 100)
	private String bairro;

	@Column(nullable = false, length = 100)
	private String cidade;

	@Column(nullable = false, length = 2)
	private String estado;

	@Column(nullable = false)
	private Boolean principal = false;

	protected Endereco() {
	}

	public Endereco(Usuario usuario, String cep, String logradouro, String numero, String complemento, String bairro,
			String cidade, String estado, Boolean principal) {
		this.usuario = usuario;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.principal = principal != null ? principal : false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	
	public void definirComoPrincipal(Endereco endereco) {
	    usuario.getEnderecos()
	           .forEach(e -> e.setPrincipal(false));
	    endereco.setPrincipal(true);
	}


}
