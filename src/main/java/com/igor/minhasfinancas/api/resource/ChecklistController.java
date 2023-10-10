package com.igor.minhasfinancas.api.resource;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.exception.ViagemNotFoundException;
import com.igor.minhasfinancas.model.entity.Checklist;
import com.igor.minhasfinancas.service.ChecklistService;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {
	
	    private final ChecklistService checklistService;

	    @Autowired
	    public ChecklistController(ChecklistService checklistService) {
	        this.checklistService = checklistService;
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

	    
	}

