package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto", indexes = { @Index(name = "idx_sku", columnList = "sku"),
		@Index(name = "idx_categoria", columnList = "categoria_id"), @Index(name = "idx_ativo", columnList = "ativo") })
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "SKU é obrigatório")
	@Size(min = 6, max = 20)
	@Column(nullable = false, unique = true, length = 20)
	private String sku;

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 200)
	@Column(nullable = false, length = 200)
	private String nome;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@NotNull(message = "Preço é obrigatório")
	@DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal preco;

	@Min(value = 0, message = "Estoque não pode ser negativo")
	@Column(nullable = false)
	private Integer estoque = 0;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal peso;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal altura;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal largura;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal profundidade;

	@Min(0)
	@Max(100)
	@Column(name = "desconto_percentual")
	private Integer descontoPercentual = 0;

	@Column(nullable = false)
	private Boolean ativo = true;

	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	public BigDecimal calcularPrecoFinal() {
		if (descontoPercentual == null || descontoPercentual == 0) {
			return preco;
		}

		BigDecimal desconto = preco.multiply(new BigDecimal(descontoPercentual)).divide(new BigDecimal(100), 2,
				RoundingMode.HALF_UP);

		return preco.subtract(desconto);
	}

	public boolean temEstoqueDisponivel(int quantidade) {
		return ativo && estoque >= quantidade;
	}

	public void decrementarEstoque(int quantidade) {
		if (quantidade > estoque) {
			throw new IllegalStateException(
					"Estoque insuficiente. Disponível: " + estoque + ", Solicitado: " + quantidade);
		}
		this.estoque -= quantidade;
	}

	public void incrementarEstoque(int quantidade) {
		this.estoque += quantidade;
	}

	public boolean estoqueCritico() {
		return estoque < 5;
	}

}
