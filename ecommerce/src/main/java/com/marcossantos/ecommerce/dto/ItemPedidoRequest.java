package com.marcossantos.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequest(
	    
		@NotBlank
	    String nomeProduto,
	    @NotBlank
        String skuProduto,
        @NotNull
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal desconto
	    
		) {

}
