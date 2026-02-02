package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
