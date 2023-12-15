package com.igor.backend.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.backend.model.entity.TipoViagemChecklistCliente;

public interface TipoViagemChecklistClienteRepository extends JpaRepository<TipoViagemChecklistCliente, UUID> {
	
	void deleteByCodigoTipoViagem(UUID codigoTipoViagem);
}






