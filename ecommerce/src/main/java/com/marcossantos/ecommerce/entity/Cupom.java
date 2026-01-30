package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.marcossantos.ecommerce.enums.TipoCupom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "cupom", indexes = { @Index(name = "idx_codigo", columnList = "codigo"),
		@Index(name = "idx_ativo", columnList = "ativo"),
		@Index(name = "idx_datas", columnList = "data_inicio, data_fim") })
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String codigo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCupom tipo;

	@Column(precision = 10, scale = 2)
	private BigDecimal valor = BigDecimal.ZERO;

	private Integer percentual = 0;

	@Column(name = "valor_minimo", precision = 10, scale = 2)
	private BigDecimal valorMinimo = BigDecimal.ZERO;

	@Column(nullable = false)
	private Integer quantidadeTotal;

	@Column(nullable = false)
	private Integer quantidadeUsada = 0;

	@Column(name = "data_inicio", nullable = false)
	private LocalDate dataInicio;

	@Column(name = "data_fim", nullable = false)
	private LocalDate dataFim;

	@Column(nullable = false)
	private Boolean ativo = true;

	public Cupom() {
	}

	public Cupom(Long id, String codigo, TipoCupom tipo, BigDecimal valor, Integer percentual, BigDecimal valorMinimo,
			Integer quantidadeTotal, Integer quantidadeUsada, LocalDate dataInicio, LocalDate dataFim, Boolean ativo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipo = tipo;
		this.valor = valor;
		this.percentual = percentual;
		this.valorMinimo = valorMinimo;
		this.quantidadeTotal = quantidadeTotal;
		this.quantidadeUsada = quantidadeUsada;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoCupom getTipo() {
		return tipo;
	}

	public void setTipo(TipoCupom tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public Integer getQuantidadeUsada() {
		return quantidadeUsada;
	}

	public void setQuantidadeUsada(Integer quantidadeUsada) {
		this.quantidadeUsada = quantidadeUsada;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@PrePersist
	@PreUpdate
	private void normalizarCodigo() {
		if (codigo != null) {
			codigo = codigo.toUpperCase().trim();
		}
	}

	public boolean isValido(LocalDate hoje, BigDecimal totalPedido) {
		if (!ativo)
			return false;
		if (hoje.isBefore(dataInicio) || hoje.isAfter(dataFim))
			return false;
		if (quantidadeUsada >= quantidadeTotal)
			return false;
		if (totalPedido.compareTo(valorMinimo) < 0)
			return false;
		return true;
	}

	public BigDecimal aplicarDesconto(BigDecimal total) {
		if (tipo == TipoCupom.PERCENTUAL) {
			return total.multiply(BigDecimal.valueOf(percentual)).divide(BigDecimal.valueOf(100));
		}
		return valor;
	}

	public void usar() {
		if (quantidadeUsada >= quantidadeTotal) {
			throw new IllegalStateException("Cupom esgotado");
		}
		quantidadeUsada++;
	}
}
