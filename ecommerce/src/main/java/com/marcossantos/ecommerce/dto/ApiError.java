package com.marcossantos.ecommerce.dto;

import java.time.LocalDateTime;

public record ApiError(int status, String mensagem, LocalDateTime timestamp) {
	public ApiError(int status, String mensagem) {
		this(status, mensagem, LocalDateTime.now());
	}
}
