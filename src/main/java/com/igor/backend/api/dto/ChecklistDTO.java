package com.igor.backend.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDTO {
	private UUID codigoChecklist;
	private String codigoExternoCategoria;
    private String codigoExternoItemChecklist;
    private LocalDateTime dataHoraInclusaoRegistro;
    private LocalDateTime dataHoraManutencaoRegistro;
    private Boolean indicadorItemChecklist;
    private List<ChecklistItemDTO> checklistCMS;
    //private String checklistUid;
    //private String checklistTitle;
    //private String checklistType;
    //private String uid; // correspondente ao uid da API
    //private String checklistItemUid; // correspondente ao uid do item do checklist na API
    //private LocalDateTime updatedAt; // correspondente à data de atualização da API
   
    @JsonIgnore
    public Short getIndicadorItemChecklistAsShort() {
        return indicadorItemChecklist != null && indicadorItemChecklist ? (short) 1 : (short) 0;
    }
    
    public Boolean getIndicadorItemChecklist() {
        // Verifique se indicadorItemChecklist é true (Boolean.TRUE)
        return indicadorItemChecklist != null && indicadorItemChecklist.equals(Boolean.TRUE);
    }
    
    public void setIndicadorItemChecklist(Boolean indicadorItemChecklist) {
        this.indicadorItemChecklist = indicadorItemChecklist;
    }
    
    

}
