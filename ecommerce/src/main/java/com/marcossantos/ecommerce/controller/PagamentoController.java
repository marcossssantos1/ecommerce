package com.marcossantos.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcossantos.ecommerce.entity.Pagamento;
import com.marcossantos.ecommerce.enums.FormaPagamento;
import com.marcossantos.ecommerce.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private final PagamentoService pagamentoService;

	public PagamentoController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	@PostMapping
	public ResponseEntity<Pagamento> criarPagamento(@RequestParam Long pedidoId,
			@RequestParam FormaPagamento formaPagamento) {

		Pagamento pagamento = pagamentoService.criarPagamento(pedidoId, formaPagamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
	}

	@PostMapping("/{id}/processar")
	public ResponseEntity<Pagamento> processarPagamento(@PathVariable Long id) {

		Pagamento pagamento = pagamentoService.processarPagamento(id);
		return ResponseEntity.ok(pagamento);
	}

	@PostMapping("/{id}/confirmar")
	public ResponseEntity<Pagamento> confirmarPagamento(@PathVariable Long id) {

		Pagamento pagamento = pagamentoService.confirmarPagamento(id);
		return ResponseEntity.ok(pagamento);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {

		return pagamentoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}
