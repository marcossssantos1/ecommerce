package com.marcossantos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcossantos.ecommerce.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
