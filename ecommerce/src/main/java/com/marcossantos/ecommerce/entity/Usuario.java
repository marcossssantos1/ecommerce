package com.marcossantos.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.marcossantos.ecommerce.enums.TipoUsuario;

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
@Table(name = "usuarios", indexes = { @Index(name = "idx_email", columnList = "email"),
		@Index(name = "idx_cpf", columnList = "cpf") })
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

	@Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;

	@NotBlank(message = "Senha é obrigatório")
	@Column(nullable = false)
	private String senha;

	@Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
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

	public Usuario() {
	}

	public Usuario(Long id, @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 200) String nome,
			@Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String email,
			@Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos") String cpf,
			@NotBlank(message = "Senha é obrigatório") String senha,
			@Pattern(regexp = "\\d{10,11}", message = "Telefone inválido") String telefone, LocalDateTime dataCadastro,
			Boolean ativo, TipoUsuario tipo, Integer tentativasLogin, LocalDateTime bloqueadoAte,
			List<Endereco> enderecos, Carrinho carrinho, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
		this.tipo = tipo;
		this.tentativasLogin = tentativasLogin;
		this.bloqueadoAte = bloqueadoAte;
		this.enderecos = enderecos;
		this.carrinho = carrinho;
		this.pedidos = pedidos;
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

	public Integer getTentativasLogin() {
		return tentativasLogin;
	}

	public void setTentativasLogin(Integer tentativasLogin) {
		this.tentativasLogin = tentativasLogin;
	}

	public LocalDateTime getBloqueadoAte() {
		return bloqueadoAte;
	}

	public void setBloqueadoAte(LocalDateTime bloqueadoAte) {
		this.bloqueadoAte = bloqueadoAte;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void adicionarEndereco(Endereco endereco) {
		enderecos.add(endereco);
		endereco.setUsuario(this);
	}

	public void bloquear(int minutos) {
		this.bloqueadoAte = LocalDateTime.now().plusMinutes(minutos);
	}

	public boolean estaBloqueado() {
		return bloqueadoAte != null && LocalDateTime.now().isBefore(bloqueadoAte);
	}

	public void resetarTentativasLogin() {
		this.tentativasLogin = 0;
		this.bloqueadoAte = null;
	}

	public void incrementarTentativasLogin() {
		this.tentativasLogin++;
		if (this.tentativasLogin >= 5) {
			bloquear(30);
		}
	}

}
