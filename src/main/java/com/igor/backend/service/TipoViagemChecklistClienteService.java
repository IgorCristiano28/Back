package com.igor.backend.service;

import java.util.List;
import java.util.UUID;

import com.igor.backend.model.entity.TipoViagemChecklistCliente;

public interface TipoViagemChecklistClienteService {
	
	TipoViagemChecklistCliente criarTipoViagem(String nomeTipoViagem);
	
	List<TipoViagemChecklistCliente> buscarTodosTiposViagem();

    TipoViagemChecklistCliente buscarTipoViagemPorId(UUID id);
    
    void excluirTipoViagemPorCodigo(UUID codigoTipoViagem);
   
}
