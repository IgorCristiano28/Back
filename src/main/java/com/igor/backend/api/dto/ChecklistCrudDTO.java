package com.igor.backend.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistCrudDTO {
	private UUID codigoChecklist;
	private String codigoExternoCategoria;
    private String codigoExternoItemChecklist;
    private LocalDateTime dataHoraInclusaoRegistro;
    private LocalDateTime dataHoraManutencaoRegistro;
    private Boolean indicadorItemChecklist;
    
    
	public void setUid(String uid) {
		// TODO Auto-generated method stub
		
	}


	public void setChecklistItemUid(String uid) {
		// TODO Auto-generated method stub
		
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		// TODO Auto-generated method stub
		
	}

}
