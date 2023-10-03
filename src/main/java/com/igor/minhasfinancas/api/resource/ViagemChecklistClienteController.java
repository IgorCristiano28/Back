package com.igor.minhasfinancas.api.resource;

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

import com.igor.minhasfinancas.api.dto.ChecklistCustomizadoDTO;
import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.api.dto.ItinerarioDTO;
import com.igor.minhasfinancas.api.dto.ViagemChecklistClienteDTO;
import com.igor.minhasfinancas.api.dto.ViagemDTO;
import com.igor.minhasfinancas.model.entity.Checklist;
import com.igor.minhasfinancas.model.entity.ChecklistCustomizado;
import com.igor.minhasfinancas.model.entity.Itinerario;
import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.entity.ViagemChecklistCliente;
import com.igor.minhasfinancas.service.ViagemChecklistClienteService;


@RestController
@RequestMapping("/api/viagem-checklist")
public class ViagemChecklistClienteController {
	
	 private final ViagemChecklistClienteService viagemChecklistClienteService;

	    @Autowired
	    public ViagemChecklistClienteController(ViagemChecklistClienteService viagemChecklistClienteService) {
	        this.viagemChecklistClienteService = viagemChecklistClienteService;
	    }

	    @PostMapping("/criar")
	    public ResponseEntity<ViagemChecklistCliente> criarViagemChecklist(@RequestBody ViagemChecklistClienteDTO viagemChecklistDTO) {
	        ViagemChecklistCliente viagemChecklist = viagemChecklistClienteService.criarViagemChecklist(viagemChecklistDTO);
	        return new ResponseEntity<>(viagemChecklist, HttpStatus.CREATED);
	    }
	    

	    @GetMapping("/tipoviagem/{tipoViagemId}")
	    public ResponseEntity<TipoViagemChecklistCliente> buscarTipoViagemPorId(@PathVariable UUID tipoViagemId) {
	        TipoViagemChecklistCliente tipoViagem = viagemChecklistClienteService.buscarTipoViagemPorId(tipoViagemId);
	        return new ResponseEntity<>(tipoViagem, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ViagemDTO> buscarViagemPorId(@PathVariable UUID id) {
	        ViagemDTO viagem = viagemChecklistClienteService.buscarViagemPorId(id);
	        return ResponseEntity.ok(viagem);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<ViagemChecklistCliente> atualizarViagem(
	            @PathVariable UUID id,
	            @RequestBody ViagemChecklistClienteDTO viagemChecklistDTO) {
	        ViagemChecklistCliente viagemChecklist = viagemChecklistClienteService.atualizarViagem(id, viagemChecklistDTO);
	        return new ResponseEntity<>(viagemChecklist, HttpStatus.OK);
	    }

	    @DeleteMapping("/{codigoViagemChecklist}")
	    public ResponseEntity<Void> excluirViagem(@PathVariable UUID codigoViagemChecklist) {
	        viagemChecklistClienteService.excluirViagem(codigoViagemChecklist);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    
	    
	    
	    
	    @GetMapping("/todas")
	    public ResponseEntity<List<ViagemChecklistCliente>> buscarTodasViagensChecklist() {
	        List<ViagemChecklistCliente> viagensChecklist = viagemChecklistClienteService.buscarTodasViagensChecklist();
	        return new ResponseEntity<>(viagensChecklist, HttpStatus.OK);
	    }

	    @PostMapping("/{codigoViagemChecklist}/itinerario/criar")
	    public ResponseEntity<Itinerario> criarItinerario(@PathVariable UUID codigoViagemChecklist, @RequestBody ItinerarioDTO itinerarioDTO) {
	        Itinerario itinerario = viagemChecklistClienteService.criarItinerario(codigoViagemChecklist, itinerarioDTO);
	        return new ResponseEntity<>(itinerario, HttpStatus.CREATED);
	    }

	    @PostMapping("/{codigoViagemChecklist}/checklist/criar")
	    public ResponseEntity<Checklist> criarChecklist(@PathVariable UUID codigoViagemChecklist, @RequestBody ChecklistDTO checklistDTO) {
	        Checklist checklist = viagemChecklistClienteService.criarChecklist(codigoViagemChecklist, checklistDTO);
	        return new ResponseEntity<>(checklist, HttpStatus.CREATED);
	    }

	    @PostMapping("/{codigoViagemChecklist}/checklistcustomizado/criar")
	    public ResponseEntity<ChecklistCustomizado> criarChecklistCustomizado(@PathVariable UUID codigoViagemChecklist, @RequestBody ChecklistCustomizadoDTO checklistCustomizadoDTO) {
	        ChecklistCustomizado checklistCustomizado = viagemChecklistClienteService.criarChecklistCustomizado(codigoViagemChecklist, checklistCustomizadoDTO);
	        return new ResponseEntity<>(checklistCustomizado, HttpStatus.CREATED);
	    }
	    
	    @GetMapping("codigo/{codigoIdentificacaoPessoa}")
	    public ResponseEntity<List<ViagemDTO>> buscarTodasViagensChecklistPessoa(
	            @PathVariable String codigoIdentificacaoPessoa) {
	        List<ViagemDTO> viagens = viagemChecklistClienteService.buscarTodasViagensChecklistPessoa(codigoIdentificacaoPessoa);
	        return ResponseEntity.ok(viagens);
	    }


}
