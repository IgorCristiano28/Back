package com.igor.backend.api.resource;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Checklist;
import com.igor.backend.service.ChecklistService;
import com.igor.backend.service.ViagemChecklistClienteService;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {
	
	    private final ChecklistService checklistService;
	    private final ViagemChecklistClienteService viagemChecklistClienteService;

	    @Autowired
	    public ChecklistController(ChecklistService checklistService, ViagemChecklistClienteService viagemChecklistClienteService) {
	        this.checklistService = checklistService;
	        this.viagemChecklistClienteService = viagemChecklistClienteService;
	    }

	    @PostMapping("viagem/{viagemId}")
	    public ResponseEntity<Checklist> criarChecklist(@PathVariable UUID viagemId, @RequestBody ChecklistDTO checklistDTO) {
	    	try {
	    	Checklist checklistCriado = checklistService.criarChecklistAPartirDeViagem(viagemId, checklistDTO);
	        return new ResponseEntity<>(checklistCriado, HttpStatus.CREATED);
	    	}catch (ViagemNotFoundException  e) {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	       }
	    }
	    
	    @PutMapping("/{checklistId}")
	    public ResponseEntity<Checklist> atualizarChecklist(@PathVariable UUID checklistId, @RequestBody ChecklistDTO checklistDTO) {
	    	 try {
	        Checklist checklistAtualizado = checklistService.atualizarChecklist(checklistId, checklistDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(checklistAtualizado);
	    	 }catch (ViagemNotFoundException  e) {
	         	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	     }
	    
	    @DeleteMapping("/{checklistId}")
	    public ResponseEntity<Void> excluirChecklist(@PathVariable UUID checklistId) {
 	    	checklistService.excluirChecklist(checklistId);
	        return ResponseEntity.noContent().build();
	    }
	    
	  }

