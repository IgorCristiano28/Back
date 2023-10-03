package com.igor.minhasfinancas.service;

import java.util.List;
import java.util.UUID;

import com.igor.minhasfinancas.api.dto.ItinerarioDTO;
import com.igor.minhasfinancas.model.entity.Itinerario;

public interface ItinerarioService {
	
	Itinerario criarItinerarioAPartirDeViagem(UUID viagemId,ItinerarioDTO itinerarioDTO);
	
	ItinerarioDTO criarItinerario(ItinerarioDTO itinerarioDTO);

    Itinerario atualizarItinerario(UUID itinerarioId, ItinerarioDTO itinerarioDTO);

    void excluirItinerario(UUID codigoChecklistItinerario);

    ItinerarioDTO buscarItinerarioPorId(UUID codigoChecklistItinerario);

    List<ItinerarioDTO> buscarItinerariosPorViagem(UUID codigoViagemChecklist);
}

