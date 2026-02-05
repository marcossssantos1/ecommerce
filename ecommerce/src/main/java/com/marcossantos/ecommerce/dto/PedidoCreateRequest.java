package com.marcossantos.ecommerce.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoCreateRequest(
		
		@NotNull
		Long usuarioId,
		
		@NotEmpty
		List<ItemPedidoRequest> itens,
		
		String observacao
		) {

}
