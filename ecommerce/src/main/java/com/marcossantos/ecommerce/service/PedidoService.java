package com.marcossantos.ecommerce.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.marcossantos.ecommerce.dto.ItemPedidoRequest;
import com.marcossantos.ecommerce.dto.PedidoCreateRequest;
import com.marcossantos.ecommerce.entity.ItemPedido;
import com.marcossantos.ecommerce.entity.Pedido;
import com.marcossantos.ecommerce.entity.Usuario;
import com.marcossantos.ecommerce.enums.StatusPedido;
import com.marcossantos.ecommerce.repository.PedidoRepository;
import com.marcossantos.ecommerce.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final UsuarioRepository usuarioRepository;

	public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
		this.pedidoRepository = pedidoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public Pedido criarPedido(PedidoCreateRequest request) {

		Usuario usuario = usuarioRepository.findById(request.usuarioId())
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setNumeroPedido(gerarNumeroPedido());
		pedido.setStatus(StatusPedido.CRIADO);
		pedido.setObservacao(request.observacao());

		BigDecimal valorTotal = BigDecimal.ZERO;

		for (ItemPedidoRequest itemReq : request.itens()) {

			BigDecimal subtotalBruto = itemReq.precoUnitario().multiply(BigDecimal.valueOf(itemReq.quantidade()));

			BigDecimal desconto = itemReq.desconto() != null ? itemReq.desconto() : BigDecimal.ZERO;

			BigDecimal subtotalLiquido = subtotalBruto.subtract(desconto);

			ItemPedido item = new ItemPedido();
			item.setPedido(pedido);
			item.setNomeProduto(itemReq.nomeProduto());
			item.setSkuProduto(itemReq.skuProduto());
			item.setQuantidade(itemReq.quantidade());
			item.setPrecoUnitario(itemReq.precoUnitario());
			item.setDesconto(desconto);
			item.setSubtotal(subtotalLiquido);

			pedido.getItens().add(item);

			valorTotal = valorTotal.add(subtotalLiquido);
		}

		pedido.setValorTotal(valorTotal);

		return pedidoRepository.save(pedido);
	}

	private String gerarNumeroPedido() {
		return "PED-" + System.currentTimeMillis();
	}
}
