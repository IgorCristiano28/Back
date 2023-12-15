package com.igor.backend.service;

import java.util.List;
import java.util.UUID;

import com.igor.backend.api.dto.ItinerarioDTO;
import com.igor.backend.model.entity.Itinerario;

public interface ItinerarioService {
	
	Itinerario criarItinerarioAPartirDeViagem(UUID viagemId,ItinerarioDTO itinerarioDTO);
	
	ItinerarioDTO criarItinerario(ItinerarioDTO itinerarioDTO);

    Itinerario atualizarItinerario(UUID itinerarioId, ItinerarioDTO itinerarioDTO);

    void excluirItinerario(UUID codigoChecklistItinerario);

    ItinerarioDTO buscarItinerarioPorId(UUID codigoChecklistItinerario);

    List<ItinerarioDTO> buscarItinerariosPorViagem(UUID codigoViagemChecklist);
}

