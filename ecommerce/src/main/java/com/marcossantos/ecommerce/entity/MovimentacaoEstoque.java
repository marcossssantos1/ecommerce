package com.marcossantos.ecommerce.entity;

import java.time.LocalDateTime;

import com.marcossantos.ecommerce.enums.TipoMovimentacaoEstoque;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacao_estoque", indexes = { @Index(name = "idx_produto", columnList = "produto_id"),
		@Index(name = "idx_tipo", columnList = "tipo"), @Index(name = "idx_data", columnList = "data_movimentacao") })
public class MovimentacaoEstoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoMovimentacaoEstoque tipo;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(name = "estoque_anterior", nullable = false)
	private Integer estoqueAnterior;

	@Column(name = "estoque_posterior", nullable = false)
	private Integer estoquePosterior;

	@Column(name = "data_movimentacao", nullable = false)
	private LocalDateTime dataMovimentacao;

	@Column(columnDefinition = "TEXT")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public MovimentacaoEstoque() {
	}

	public MovimentacaoEstoque(Long id, Produto produto, TipoMovimentacaoEstoque tipo, Integer quantidade,
			Integer estoqueAnterior, Integer estoquePosterior, LocalDateTime dataMovimentacao, String observacao,
			Pedido pedido) {
		super();
		this.id = id;
		this.produto = produto;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.estoqueAnterior = estoqueAnterior;
		this.estoquePosterior = estoquePosterior;
		this.dataMovimentacao = dataMovimentacao;
		this.observacao = observacao;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoMovimentacaoEstoque getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacaoEstoque tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getEstoqueAnterior() {
		return estoqueAnterior;
	}

	public void setEstoqueAnterior(Integer estoqueAnterior) {
		this.estoqueAnterior = estoqueAnterior;
	}

	public Integer getEstoquePosterior() {
		return estoquePosterior;
	}

	public void setEstoquePosterior(Integer estoquePosterior) {
		this.estoquePosterior = estoquePosterior;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@PrePersist
	private void prePersist() {
		if (dataMovimentacao == null) {
			dataMovimentacao = LocalDateTime.now();
		}
	}
}
