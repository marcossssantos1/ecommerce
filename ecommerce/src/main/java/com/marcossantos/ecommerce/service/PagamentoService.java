package com.marcossantos.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.entity.Pagamento;
import com.marcossantos.ecommerce.entity.Pedido;
import com.marcossantos.ecommerce.enums.FormaPagamento;
import com.marcossantos.ecommerce.enums.StatusPagamento;
import com.marcossantos.ecommerce.enums.StatusPedido;
import com.marcossantos.ecommerce.repository.PagamentoRepository;
import com.marcossantos.ecommerce.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Pagamento criarPagamento(Long pedidoId, FormaPagamento formaPagamento) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.CONFIRMADO) {
            throw new RuntimeException("Pedido não está confirmado para pagamento");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setStatus(StatusPagamento.CRIADO);
        pagamento.setValor(pedido.getValorTotal());

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento processarPagamento(Long pagamentoId) {

        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (pagamento.getStatus() != StatusPagamento.CRIADO) {
            throw new RuntimeException("Pagamento não pode ser processado");
        }

        pagamento.setStatus(StatusPagamento.PROCESSANDO);

        switch (pagamento.getFormaPagamento()) {

            case PIX:
                pagamento.setStatus(StatusPagamento.PENDENTE);
                pagamento.setQrcodePix("QR_CODE_PIX_GERADO");
                pagamento.setCodigoTransacao(UUID.randomUUID().toString());
                break;

            case BOLETO:
                pagamento.setStatus(StatusPagamento.PENDENTE);
                pagamento.setCodigoBarrasBoleto("CODIGO_DE_BARRAS_GERADO");
                pagamento.setDataVencimento(LocalDate.now().plusDays(3));
                pagamento.setCodigoTransacao(UUID.randomUUID().toString());
                break;

            case CREDITO:
                pagamento.setCodigoTransacao(UUID.randomUUID().toString());

                if (pagamento.getValor().compareTo(new BigDecimal("1000")) <= 0) {
                    pagamento.setStatus(StatusPagamento.APROVADO);
                    pagamento.setDataAprovacao(LocalDateTime.now());

                    marcarPedidoComoPago(pagamento.getPedido());
                } else {
                    pagamento.setStatus(StatusPagamento.RECUSADO);
                    pagamento.setMotivoRecusa("Limite insuficiente");
                }
                break;
        }

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento confirmarPagamento(Long pagamentoId) {

        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (pagamento.getStatus() != StatusPagamento.PENDENTE) {
            throw new RuntimeException("Pagamento não está pendente");
        }

        pagamento.setStatus(StatusPagamento.APROVADO);
        pagamento.setDataAprovacao(LocalDateTime.now());

        marcarPedidoComoPago(pagamento.getPedido());

        return pagamentoRepository.save(pagamento);
    }

    private void marcarPedidoComoPago(Pedido pedido) {
        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);
    }
    
    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

}

