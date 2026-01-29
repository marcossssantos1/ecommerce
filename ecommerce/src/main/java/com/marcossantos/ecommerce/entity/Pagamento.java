package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.marcossantos.ecommerce.enums.FormaPagamento;
import com.marcossantos.ecommerce.enums.StatusPagamento;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento", indexes = { @Index(name = "idx_pedido", columnList = "pedido_id"),
		@Index(name = "idx_status", columnList = "status"),
		@Index(name = "idx_codigo_transacao", columnList = "codigo_transacao") })

public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", nullable = false, unique = true)
	private Pedido pedido;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPagamento status = StatusPagamento.PENDENTE;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(name = "numero_parcelas")
	private Integer numeroParcelas = 1;

	@Column(name = "valor_parcela", precision = 10, scale = 2)
	private BigDecimal valorParcela;

	@Column(name = "codigo_transacao", length = 100)
	private String codigoTransacao;

	@Column(name = "qrcode_pix", columnDefinition = "TEXT")
	private String qrCodePix;

	@Column(name = "codigo_barras_boleto", length = 100)
	private String codigoBarrasBoleto;

	@Column(name = "data_pagamento", nullable = false)
	private LocalDateTime dataPagamento;

	@Column(name = "data_aprovacao")
	private LocalDateTime dataAprovacao;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@PrePersist
	private void prePersist() {
		if (dataPagamento == null) {
			dataPagamento = LocalDateTime.now();
		}

		if (formaPagamento == FormaPagamento.CREDITO && valorParcela == null) {
			valorParcela = valor.divide(BigDecimal.valueOf(numeroParcelas), 2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public Pagamento() {
	}

	public Pagamento(Long id, Pedido pedido, FormaPagamento formaPagamento, StatusPagamento status, BigDecimal valor,
			Integer numeroParcelas, BigDecimal valorParcela, String codigoTransacao, String qrCodePix,
			String codigoBarrasBoleto, LocalDateTime dataPagamento, LocalDateTime dataAprovacao,
			LocalDate dataVencimento) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.formaPagamento = formaPagamento;
		this.status = status;
		this.valor = valor;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.codigoTransacao = codigoTransacao;
		this.qrCodePix = qrCodePix;
		this.codigoBarrasBoleto = codigoBarrasBoleto;
		this.dataPagamento = dataPagamento;
		this.dataAprovacao = dataAprovacao;
		this.dataVencimento = dataVencimento;
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

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	public String getQrCodePix() {
		return qrCodePix;
	}

	public void setQrCodePix(String qrCodePix) {
		this.qrCodePix = qrCodePix;
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

}
