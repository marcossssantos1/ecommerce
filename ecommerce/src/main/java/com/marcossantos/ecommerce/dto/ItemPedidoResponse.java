package com.marcossantos.ecommerce.dto;

import java.math.BigDecimal;

import com.marcossantos.ecommerce.entity.ItemPedido;

public record ItemPedidoResponse(
        String nomeProduto,
        String skuProduto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal desconto,
        BigDecimal subtotal
) {
    public static ItemPedidoResponse from(ItemPedido item) {
        return new ItemPedidoResponse(
                item.getNomeProduto(),
                item.getSkuProduto(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getDesconto(),
                item.getSubtotal()
        );
    }
}
