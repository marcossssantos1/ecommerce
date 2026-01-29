package com.marcossantos.ecommerce.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_carrinho", uniqueConstraints = {
		@UniqueConstraint(name = "uk_carrinho_produto", columnNames = { "carrinho_id", "produto_id" }) })
public class ItemCarrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrinho_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_carrinho_carrinho"))
	private Carrinho carrinho;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_carrinho_produto"))
	private Produto produto;

	@Column(nullable = false)
	private Integer quantidade = 1;

	@Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal precoUnitario;

	protected ItemCarrinho() {
	}

	public ItemCarrinho(Carrinho carrinho, Produto produto, Integer quantidade, BigDecimal precoUnitario) {
		this.carrinho = carrinho;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public BigDecimal calcularSubtotal() {
	    return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}

}
