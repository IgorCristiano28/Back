package com.igor.backend.service;

import java.util.List;
import java.util.UUID;

import com.igor.backend.api.dto.ChecklistCustomizadoDTO;
import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.api.dto.ChecklistDTOCMS;
import com.igor.backend.api.dto.ChecklistItemApiResponseDTO;
import com.igor.backend.api.dto.ChecklistItemDTO;
import com.igor.backend.api.dto.ItinerarioDTO;
import com.igor.backend.api.dto.ViagemChecklistClienteDTO;
import com.igor.backend.api.dto.ViagemDTO;
import com.igor.backend.model.entity.Checklist;
import com.igor.backend.model.entity.ChecklistCustomizado;
import com.igor.backend.model.entity.ChecklistEntry;
import com.igor.backend.model.entity.Entry;
import com.igor.backend.model.entity.Itinerario;
import com.igor.backend.model.entity.TipoViagemChecklistCliente;
import com.igor.backend.model.entity.ViagemChecklistCliente;

public interface ViagemChecklistClienteService {
  
	    ViagemChecklistCliente criarViagemChecklist(ViagemChecklistClienteDTO viagemChecklistDTO);
	    
	    List<ViagemChecklistCliente> buscarTodasViagensChecklist();
	    
	    ViagemDTO buscarViagemPorId(UUID id);
	    
	    ViagemChecklistCliente atualizarViagem(UUID id, ViagemChecklistClienteDTO viagemChecklistDTO);
	    
	    void excluirViagem(UUID id);
	    
	    TipoViagemChecklistCliente buscarTipoViagemPorId(UUID tipoViagemId);
	    
	    Itinerario criarItinerario(UUID codigoViagemChecklist, ItinerarioDTO itinerarioDTO);
	    
	    Checklist criarChecklist(UUID codigoViagemChecklist, ChecklistDTO checklistDTO);
	    
	    ChecklistCustomizado criarChecklistCustomizado(UUID codigoViagemChecklist, ChecklistCustomizadoDTO checklistCustomizadoDTO);
	    
	    List<ViagemDTO> buscarTodasViagensChecklistPessoa(String codigoIdentificacaoPessoa);
	    // Outros métodos, se necessário

		List<ChecklistDTOCMS> consultarCMS();

	}

