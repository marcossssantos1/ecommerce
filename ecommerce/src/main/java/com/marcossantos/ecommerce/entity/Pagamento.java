package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.marcossantos.ecommerce.enums.FormaPagamento;
import com.marcossantos.ecommerce.enums.StatusPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento", indexes = { @Index(name = "idx_status", columnList = "status"),
		@Index(name = "idx_forma_pagamento", columnList = "forma_pagamento"),
		@Index(name = "idx_codigo_transacao", columnList = "codigo_transacao"),
		@Index(name = "idx_data_pagamento", columnList = "data_pagamento") })

public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id", unique = true)
	private Pedido pedido;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPagamento status;

	@Column(nullable = false)
	private BigDecimal valor;

	private Integer numeroParcelas;

	private BigDecimal valorParcela;

	private BigDecimal juros;

	@Column(unique = true)
	private String codigoTransacao;

	@Column(columnDefinition = "TEXT")
	private String qrcodePix;

	private String codigoBarrasBoleto;

	@CreationTimestamp
	@Column(name = "data_pagamento", updatable = false)
	private LocalDateTime dataPagamento;

	private LocalDateTime dataAprovacao;

	private LocalDate dataVencimento;

	@Column(columnDefinition = "TEXT")
	private String motivoRecusa;

	public Pagamento() {
	}

	public Pagamento(Long id, Pedido pedido, FormaPagamento formaPagamento, StatusPagamento status, BigDecimal valor,
			Integer numeroParcelas, BigDecimal valorParcela, BigDecimal juros, String codigoTransacao, String qrcodePix,
			String codigoBarrasBoleto, LocalDateTime dataPagamento, LocalDateTime dataAprovacao,
			LocalDate dataVencimento, String motivoRecusa) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.formaPagamento = formaPagamento;
		this.status = status;
		this.valor = valor;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.juros = juros;
		this.codigoTransacao = codigoTransacao;
		this.qrcodePix = qrcodePix;
		this.codigoBarrasBoleto = codigoBarrasBoleto;
		this.dataPagamento = dataPagamento;
		this.dataAprovacao = dataAprovacao;
		this.dataVencimento = dataVencimento;
		this.motivoRecusa = motivoRecusa;
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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	public String getQrcodePix() {
		return qrcodePix;
	}

	public void setQrcodePix(String qrcodePix) {
		this.qrcodePix = qrcodePix;
	}

	public String getCodigoBarrasBoleto() {
		return codigoBarrasBoleto;
	}

	public void setCodigoBarrasBoleto(String codigoBarrasBoleto) {
		this.codigoBarrasBoleto = codigoBarrasBoleto;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public LocalDateTime getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(LocalDateTime dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getMotivoRecusa() {
		return motivoRecusa;
	}

	public void setMotivoRecusa(String motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

}
