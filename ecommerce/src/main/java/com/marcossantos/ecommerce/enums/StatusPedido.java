package com.marcossantos.ecommerce.enums;

public enum StatusPedido {

	PENDENTE("Pendente"), CONFIRMADO("Confirmado"), EM_SEPARACAO("Em Separação"), ENVIADO("Enviado"),
	ENTREGUE("Entregue"), CANCELADO("Cancelado");

	private final String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
