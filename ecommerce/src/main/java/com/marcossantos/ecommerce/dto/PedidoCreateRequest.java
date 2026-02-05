package com.marcossantos.ecommerce.dto;

import java.util.List;

import com.marcossantos.ecommerce.entity.ItemPedido;
import com.marcossantos.ecommerce.entity.Usuario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoCreateRequest(
		
		@NotNull
		Long usuarioId,
		
		@NotEmpty
		List<ItemPedido> itens,
		
		String observacao
		) {

}
