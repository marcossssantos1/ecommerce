package com.marcossantos.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.marcossantos.ecommerce.entity.Pedido;
import com.marcossantos.ecommerce.enums.StatusPedido;

public record PedidoResponse(
        Long id,
        String numeroPedido,
        BigDecimal valorTotal,
        StatusPedido status,
        LocalDateTime dataPedido,
        String observacao,
        List<ItemPedidoResponse> itens
) {
    public static PedidoResponse from(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getNumeroPedido(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataPedido(),
                pedido.getObservacao(),
                pedido.getItens().stream()
                        .map(ItemPedidoResponse::from)
                        .toList()
        );
    }
}
