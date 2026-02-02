package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido", indexes = { @Index(name = "idx_pedido", columnList = "pedido_id"),
		@Index(name = "idx_sku", columnList = "sku_produto") })

public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@Column(name = "nome_produto", nullable = false)
	private String nomeProduto;

	@Column(name = "sku_produto", nullable = false)
	private String skuProduto;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal precoUnitario;

	@Column(nullable = false)
	private BigDecimal subtotal;

	@Column(nullable = false)
	private BigDecimal desconto;

	public ItemPedido() {
	}

	public ItemPedido(Long id, Pedido pedido, String nomeProduto, String skuProduto, Integer quantidade,
			BigDecimal precoUnitario, BigDecimal subtotal, BigDecimal desconto) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.nomeProduto = nomeProduto;
		this.skuProduto = skuProduto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.subtotal = subtotal;
		this.desconto = desconto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getSkuProduto() {
		return skuProduto;
	}

	public void setSkuProduto(String skuProduto) {
		this.skuProduto = skuProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

}
