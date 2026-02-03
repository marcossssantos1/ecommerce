package com.marcossantos.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marcossantos.ecommerce.dto.ApiError;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException ex) {

		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<ApiError> handleRegraNegocio(UsuarioException ex) {

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

		String mensagem = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage()).findFirst().orElse("Dados inválidos");

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), mensagem);

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex) {

		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno do servidor");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
