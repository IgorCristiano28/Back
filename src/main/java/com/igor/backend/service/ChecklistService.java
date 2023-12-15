package com.igor.backend.service;

import java.util.UUID;

import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.model.entity.Checklist;

public interface ChecklistService {

	Checklist criarChecklistAPartirDeViagem(UUID viagemId, ChecklistDTO checklistDTO);

	Checklist atualizarChecklist(UUID checklistId, ChecklistDTO checklistDTO);

	void excluirChecklist(UUID checklistId);
}
