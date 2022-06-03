package com.igor.minhasfinancas.service;

import java.util.List;

import com.igor.minhasfinancas.model.entity.Lancamento;
import com.igor.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	//vai receber um lancamento q não esta salvo na base de dados. 
	Lancamento salvar (Lancamento lancamento);
	//ja tem q estar com id
	Lancamento atualizar (Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	//buscar, vai retorna uma lista de lancamento
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
    void atualizarStatus(Lancamento lancamento, StatusLancamento status);
    
    void validar (Lancamento lancamento);
	

}
