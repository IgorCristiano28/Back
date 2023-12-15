package com.igor.backend.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.backend.api.dto.ItinerarioDTO;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Itinerario;
import com.igor.backend.service.ItinerarioService;

@RestController
@RequestMapping("/api/itinerarios")
public class ItinerarioController {

    private final ItinerarioService itinerarioService;

    @Autowired
    public ItinerarioController(ItinerarioService itinerarioService) {
        this.itinerarioService = itinerarioService;
    }

    @PostMapping("viagem/{viagemId}")
    public ResponseEntity<Itinerario> criarItinerarioAPartirDeViagem(
            @PathVariable UUID viagemId,
            @RequestBody ItinerarioDTO itinerarioDTO
        ){
        try {
        Itinerario itinerarioCriado  = itinerarioService.criarItinerarioAPartirDeViagem(viagemId, itinerarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itinerarioCriado);
        }catch (ViagemNotFoundException  e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
        
        
	}
   
    @PutMapping("viagem/{itinerarioId}")
    public ResponseEntity<Itinerario> atualizarItinerario(
            @PathVariable UUID itinerarioId,
            @RequestBody ItinerarioDTO itinerarioDTO) {
        Itinerario itinerarioAtualizado = itinerarioService.atualizarItinerario(itinerarioId, itinerarioDTO);
        if (itinerarioAtualizado != null) {
            return ResponseEntity.ok(itinerarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/viagem/{codigoChecklistItinerario}")
    public ResponseEntity<Void> excluirItinerario(@PathVariable UUID codigoChecklistItinerario) {
        itinerarioService.excluirItinerario(codigoChecklistItinerario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigoChecklistItinerario}")
    public ResponseEntity<ItinerarioDTO> buscarItinerarioPorId(@PathVariable UUID codigoChecklistItinerario) {
        ItinerarioDTO itinerario = itinerarioService.buscarItinerarioPorId(codigoChecklistItinerario);
        if (itinerario != null) {
            return ResponseEntity.ok(itinerario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/viagem/{codigoViagemChecklist}")
    public ResponseEntity<List<ItinerarioDTO>> buscarItinerariosPorViagem(@PathVariable UUID codigoViagemChecklist) {
        List<ItinerarioDTO> itinerarios = itinerarioService.buscarItinerariosPorViagem(codigoViagemChecklist);
        return ResponseEntity.ok(itinerarios);
    }
}

