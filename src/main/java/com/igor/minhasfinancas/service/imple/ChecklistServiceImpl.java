package com.igor.minhasfinancas.service.imple;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.exception.ViagemNotFoundException;
import com.igor.minhasfinancas.model.entity.Checklist;
import com.igor.minhasfinancas.model.entity.ViagemChecklistCliente;
import com.igor.minhasfinancas.model.repository.ChecklistRepository;
import com.igor.minhasfinancas.model.repository.ItinerarioRepository;
import com.igor.minhasfinancas.model.repository.ViagemChecklistClienteRepository;
import com.igor.minhasfinancas.service.ChecklistService;
import com.igor.minhasfinancas.service.ViagemChecklistClienteService;

@Service
public class ChecklistServiceImpl implements ChecklistService {
	
	private final ChecklistRepository checklistRepository;
    private final ViagemChecklistClienteRepository viagemChecklistClienteRepository;
    private final ViagemChecklistClienteService viagemChecklistClienteService;
    

    @Autowired
    public ChecklistServiceImpl(ChecklistRepository checklistRepository, ViagemChecklistClienteRepository viagemChecklistClienteRepository,
    		ViagemChecklistClienteService viagemChecklistClienteService) {
        this.checklistRepository = checklistRepository;
        this.viagemChecklistClienteRepository = viagemChecklistClienteRepository;
        this.viagemChecklistClienteService = viagemChecklistClienteService;
    }

    @Override
    public Checklist criarChecklistAPartirDeViagem(UUID viagemId, ChecklistDTO checklistDTO) {
    	
    	if (viagemId == null || checklistDTO == null) {
	        throw new IllegalArgumentException("viagemId e itinerarioDTO não podem ser nulos.");
	    }
    	
    	 // Verifica se a viagem existe
        ViagemChecklistCliente viagem = viagemChecklistClienteRepository.findById(viagemId)
                .orElseThrow(() -> new ViagemNotFoundException("Viagem com ID " + viagemId + " não encontrada."));
    	
    	// Cria um checklit a partir do DTO
    	Checklist checklist = new Checklist();
    	checklist.setCodigoExternoCategoria(checklistDTO.getCodigoExternoCategoria());
    	checklist.setCodigoExternoItemChecklist(checklistDTO.getCodigoExternoItemChecklist());
    	
    	// Atribui o valor do indicadorItemChecklist após a conversão
    	checklist.setIndicadorItemChecklist((short) (checklistDTO.getIndicadorItemChecklist() ? 1 : 0));
    	
    	//atualiza data e hora da inclusao registro
    	checklist.setDataHoraInclusaoRegistro(LocalDateTime.now());
    	
    	//Associa o checklist a viagem
    	checklist.setViagemChecklist(viagem.getCodigoViagemChecklist());
    	
    	//Salva o novo checklist no banco de dados usando o repositório ChecklistRepository
    	
    	return checklistRepository.save(checklist);
    }

}
