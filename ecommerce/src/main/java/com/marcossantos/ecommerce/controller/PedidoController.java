package com.marcossantos.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcossantos.ecommerce.dto.PedidoCreateRequest;
import com.marcossantos.ecommerce.dto.PedidoResponse;
import com.marcossantos.ecommerce.entity.Pedido;
import com.marcossantos.ecommerce.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(
            @Valid @RequestBody PedidoCreateRequest request) {

        Pedido pedido = pedidoService.criarPedido(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PedidoResponse.from(pedido));
    }
}
