package com.igor.backend.service.imple;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.api.dto.ViagemDTO;
import com.igor.backend.exception.ChecklistNotFoundException;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Checklist;
import com.igor.backend.model.entity.ViagemChecklistCliente;
import com.igor.backend.model.repository.ChecklistRepository;
import com.igor.backend.model.repository.ViagemChecklistClienteRepository;
import com.igor.backend.service.ChecklistService;
import com.igor.backend.service.ViagemChecklistClienteService;

@Service
public class ChecklistServiceImpl implements ChecklistService {
	
	private final ChecklistRepository checklistRepository;
    private final ViagemChecklistClienteRepository viagemChecklistClienteRepository;
    

    @Autowired
    public ChecklistServiceImpl(ChecklistRepository checklistRepository, ViagemChecklistClienteRepository viagemChecklistClienteRepository,
    		ViagemChecklistClienteService viagemChecklistClienteService) {
        this.checklistRepository = checklistRepository;
        this.viagemChecklistClienteRepository = viagemChecklistClienteRepository;
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

	@Override
	public Checklist atualizarChecklist(UUID checklistId, ChecklistDTO checklistDTO) {
		 Checklist checklistExistente = checklistRepository.findById(checklistId)
	                .orElseThrow(() -> new RuntimeException("Checklist com ID " + checklistId + " não encontrado."));

		// Verificar e atualizar os campos não nulos no DTO
		    if (checklistDTO.getCodigoExternoCategoria() != null) {
		        checklistExistente.setCodigoExternoCategoria(checklistDTO.getCodigoExternoCategoria());
		    }

		    if (checklistDTO.getCodigoExternoItemChecklist() != null) {
		        checklistExistente.setCodigoExternoItemChecklist(checklistDTO.getCodigoExternoItemChecklist());
		    }

		    // Converter booleano para Short
		    if (checklistDTO.getIndicadorItemChecklist() != null) {
		        checklistExistente.setIndicadorItemChecklist(checklistDTO.getIndicadorItemChecklist() ? (short) 1 : (short) 0);
		    }

		    // Atualizar o checklist no banco de dados
		    return checklistRepository.save(checklistExistente);
	        
	}

	@Override
	public void excluirChecklist(UUID checklistId) {
		 // Verifique se o checkList com o ID fornecido existe
        Checklist checklist = checklistRepository.findById(checklistId)
                .orElseThrow(() -> new ChecklistNotFoundException("Checklist não encontrado"));

        // Remova o checklist do banco de dados
        checklistRepository.delete(checklist);
		
	}
}	