package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinho")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "usuario_id", nullable = false, unique = true)
	private Usuario usuario;

	@CreationTimestamp
	@Column(name = "data_criacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	@Column(name = "data_ultima_atualizacao", nullable = false)
	private LocalDateTime dataUltimaAtualizacao;

	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCarrinho> itens = new ArrayList<>();

	public Carrinho() {
	}

	public Carrinho(Long id, Usuario usuario, LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao,
			List<ItemCarrinho> itens) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.dataCriacao = dataCriacao;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.itens = itens;
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public void adicionarItem(Produto produto, int quantidade) {
		ItemCarrinho itemExistente = itens.stream().filter(item -> item.getProduto().getId().equals(produto.getId()))
				.findFirst().orElse(null);

		if (itemExistente != null) {
			itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
		} else {
			ItemCarrinho novoItem = new ItemCarrinho(this, produto, quantidade, produto.calcularPrecoFinal());
			itens.add(novoItem);
		}
	}

	public void removerItem(Long itemId) {
		itens.removeIf(item -> item.getId().equals(itemId));
	}

	public void limpar() {
		itens.clear();
	}

	public BigDecimal calcularSubtotal() {
		return itens.stream().map(ItemCarrinho::calcularSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public boolean verificarDisponibilidade() {
		return itens.stream().allMatch(item -> item.getProduto().temEstoqueDisponivel(item.getQuantidade()));
	}

	public int getTotalItens() {
		return itens.stream().mapToInt(ItemCarrinho::getQuantidade).sum();
	}
}