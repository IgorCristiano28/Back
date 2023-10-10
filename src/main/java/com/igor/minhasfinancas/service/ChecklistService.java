package com.igor.minhasfinancas.service;

import java.util.UUID;

import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.model.entity.Checklist;

public interface ChecklistService {

	Checklist criarChecklistAPartirDeViagem(UUID viagemId, ChecklistDTO checklistDTO);

}
