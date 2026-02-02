package com.marcossantos.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public Pedido() {
	}

	public Pedido(Long id, Usuario usuario, String numeroPedido, BigDecimal valorTotal, StatusPedido status,
			LocalDateTime dataPedido, LocalDateTime dataConfirmacao, LocalDateTime dataCancelamento, String observacao,
			List<ItemPedido> itens, Pagamento pagamento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.numeroPedido = numeroPedido;
		this.valorTotal = valorTotal;
		this.status = status;
		this.dataPedido = dataPedido;
		this.dataConfirmacao = dataConfirmacao;
		this.dataCancelamento = dataCancelamento;
		this.observacao = observacao;
		this.itens = itens;
		this.pagamento = pagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDateTime getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

}
