package com.igor.backend.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.backend.model.entity.ViagemChecklistCliente;

public interface ViagemChecklistClienteRepository extends JpaRepository<ViagemChecklistCliente, UUID> {
	void deleteBycodigoViagemChecklist(UUID codigoViagemChecklist);
	
	List<ViagemChecklistCliente> findByCodigoIdentificacaoPessoa(String codigoIdentificacaoPessoa);
}
