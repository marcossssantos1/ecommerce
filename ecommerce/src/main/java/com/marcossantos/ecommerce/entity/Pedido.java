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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pedido", indexes = { @Index(name = "idx_numero_pedido", columnList = "numero_pedido"),
		@Index(name = "idx_usuario", columnList = "usuario_id"), @Index(name = "idx_status", columnList = "status") })
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_pedido", nullable = false, unique = true, length = 30)
	private String numeroPedido;

	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal subtotal;

	@NotNull
	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorFrete = BigDecimal.ZERO;

	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal desconto = BigDecimal.ZERO;

	@NotNull
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPedido status = StatusPedido.PENDENTE;

	@CreationTimestamp
	@Column(name = "data_pedido", nullable = false, updatable = false)
	private LocalDateTime dataPedido;

	@Column(name = "prazo_entrega")
	private Integer prazoEntrega;

	@Column(name = "codigo_rastreamento", length = 50)
	private String codigoRastreamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cupom_id")
	private Cupom cupom;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<HistoricoPedido> historico = new ArrayList<>();

	public Pedido() {
	}

	public Pedido(Long id, String numeroPedido, @NotNull BigDecimal subtotal, @NotNull BigDecimal valorFrete,
			@NotNull BigDecimal desconto, @NotNull BigDecimal valorTotal, StatusPedido status, LocalDateTime dataPedido,
			Integer prazoEntrega, String codigoRastreamento, Usuario usuario, Endereco endereco, Cupom cupom,
			List<ItemPedido> itens, Pagamento pagamento, List<HistoricoPedido> historico) {
		super();
		this.id = id;
		this.numeroPedido = numeroPedido;
		this.subtotal = subtotal;
		this.valorFrete = valorFrete;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.status = status;
		this.dataPedido = dataPedido;
		this.prazoEntrega = prazoEntrega;
		this.codigoRastreamento = codigoRastreamento;
		this.usuario = usuario;
		this.endereco = endereco;
		this.cupom = cupom;
		this.itens = itens;
		this.pagamento = pagamento;
		this.historico = historico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
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

	public Integer getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(Integer prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getCodigoRastreamento() {
		return codigoRastreamento;
	}

	public void setCodigoRastreamento(String codigoRastreamento) {
		this.codigoRastreamento = codigoRastreamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
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

	public List<HistoricoPedido> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoPedido> historico) {
		this.historico = historico;
	}

	public void calcularTotal() {
		this.valorTotal = subtotal.add(valorFrete).subtract(desconto);
	}

	public void adicionarItem(ItemPedido item) {
		itens.add(item);
		item.setPedido(this);
	}

	public void confirmar() {
		if (status != StatusPedido.PENDENTE) {
			throw new IllegalStateException("Apenas pedidos pendentes podem ser confirmados");
		}
		this.status = StatusPedido.CONFIRMADO;
	}

	public void cancelar() {
		if (status != StatusPedido.PENDENTE && status != StatusPedido.CONFIRMADO) {
			throw new IllegalStateException("Apenas pedidos pendentes ou confirmados podem ser cancelados");
		}
		this.status = StatusPedido.CANCELADO;
	}

	public void atualizarStatus(StatusPedido novoStatus) {
		StatusPedido statusAnterior = this.status;
		this.status = novoStatus;

		HistoricoPedido hist = new HistoricoPedido();
		hist.setPedido(this);
		hist.setStatusAnterior(statusAnterior);
		hist.setStatusNovo(novoStatus);

		historico.add(hist);
	}

	public boolean podeCancelar() {
		return status == StatusPedido.PENDENTE || status == StatusPedido.CONFIRMADO;
	}
}