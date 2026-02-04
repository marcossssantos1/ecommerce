package com.marcossantos.ecommerce.service;

import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	
	private final PedidoRepository repository;
	
	public PedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

}
