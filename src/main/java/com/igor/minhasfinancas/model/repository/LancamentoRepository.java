package com.igor.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
	

}
