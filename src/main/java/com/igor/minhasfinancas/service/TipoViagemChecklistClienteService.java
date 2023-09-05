package com.igor.minhasfinancas.service;

import java.util.List;
import java.util.UUID;

import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;

public interface TipoViagemChecklistClienteService {
	
	TipoViagemChecklistCliente criarTipoViagem(String nomeTipoViagem);
	
	List<TipoViagemChecklistCliente> buscarTodosTiposViagem();

    TipoViagemChecklistCliente buscarTipoViagemPorId(UUID id);
    
    void excluirTipoViagemPorCodigo(UUID codigoTipoViagem);
   
}
