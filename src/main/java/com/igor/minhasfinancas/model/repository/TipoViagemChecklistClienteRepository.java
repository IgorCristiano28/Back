package com.igor.minhasfinancas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;

public interface TipoViagemChecklistClienteRepository extends JpaRepository<TipoViagemChecklistCliente, UUID> {
	
	void deleteByCodigoTipoViagem(UUID codigoTipoViagem);
}






