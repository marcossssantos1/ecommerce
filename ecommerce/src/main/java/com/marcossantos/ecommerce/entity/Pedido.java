package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.marcossantos.ecommerce.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido", indexes = { @Index(name = "idx_usuario", columnList = "usuario_id"),
		@Index(name = "idx_numero_pedido", columnList = "numero_pedido"),
		@Index(name = "idx_status", columnList = "status"),
		@Index(name = "idx_data_pedido", columnList = "data_pedido") })

public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Column(name = "numero_pedido", nullable = false, unique = true, length = 30)
	private String numeroPedido;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPedido status;

	@CreationTimestamp
	@Column(name = "data_pedido", updatable = false)
	private LocalDateTime dataPedido;

	private LocalDateTime dataConfirmacao;

	private LocalDateTime dataCancelamento;

	@Column(columnDefinition = "TEXT")
	private String observacao;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
}
