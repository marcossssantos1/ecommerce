package com.marcossantos.ecommerce.entity;

import java.time.LocalDateTime;

import com.marcossantos.ecommerce.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "historico_pedido")
public class HistoricoPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_anterior")
	private StatusPedido statusAnterior;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_novo", nullable = false)
	private StatusPedido statusNovo;

	@Column(name = "data_alteracao", nullable = false)
	private LocalDateTime dataAlteracao;

	@Column(columnDefinition = "TEXT")
	private String observacao;

	@PrePersist
	private void prePersist() {
		this.dataAlteracao = LocalDateTime.now();
	}

	public HistoricoPedido() {
	}

	public HistoricoPedido(Long id, Pedido pedido, StatusPedido statusAnterior, StatusPedido statusNovo,
			LocalDateTime dataAlteracao, String observacao) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.statusAnterior = statusAnterior;
		this.statusNovo = statusNovo;
		this.dataAlteracao = dataAlteracao;
		this.observacao = observacao;
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

	public StatusPedido getStatusAnterior() {
		return statusAnterior;
	}

	public void setStatusAnterior(StatusPedido statusAnterior) {
		this.statusAnterior = statusAnterior;
	}

	public StatusPedido getStatusNovo() {
		return statusNovo;
	}

	public void setStatusNovo(StatusPedido statusNovo) {
		this.statusNovo = statusNovo;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
