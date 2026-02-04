package com.marcossantos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcossantos.ecommerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
