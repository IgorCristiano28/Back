package com.igor.minhasfinancas.service;

import java.util.List;
import java.util.UUID;

import com.igor.minhasfinancas.api.dto.ChecklistCustomizadoDTO;
import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.api.dto.ItinerarioDTO;
import com.igor.minhasfinancas.api.dto.ViagemChecklistClienteDTO;
import com.igor.minhasfinancas.model.entity.Checklist;
import com.igor.minhasfinancas.model.entity.ChecklistCustomizado;
import com.igor.minhasfinancas.model.entity.Itinerario;
import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.entity.ViagemChecklistCliente;

public interface ViagemChecklistClienteService {
  
	    ViagemChecklistCliente criarViagemChecklist(ViagemChecklistClienteDTO viagemChecklistDTO);
	    
	    List<ViagemChecklistCliente> buscarTodasViagensChecklist();
	    
	    ViagemChecklistCliente buscarViagemPorId(UUID id);
	    
	    ViagemChecklistCliente atualizarViagem(UUID id, ViagemChecklistClienteDTO viagemChecklistDTO);
	    
	    void excluirViagem(UUID id);
	    
	    TipoViagemChecklistCliente buscarTipoViagemPorId(UUID tipoViagemId);
	    
	    Itinerario criarItinerario(UUID codigoViagemChecklist, ItinerarioDTO itinerarioDTO);
	    
	    Checklist criarChecklist(UUID codigoViagemChecklist, ChecklistDTO checklistDTO);
	    
	    ChecklistCustomizado criarChecklistCustomizado(UUID codigoViagemChecklist, ChecklistCustomizadoDTO checklistCustomizadoDTO);
	    
	    // Outros métodos, se necessário
	}

