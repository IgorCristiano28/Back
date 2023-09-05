package com.igor.minhasfinancas.api.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDTO {
	private String codigoExternoCategoria;
    private String codigoExternoItemChecklist;
    private Date dataHoraInclusaoRegistro;
    private Date dataHoraManutencaoRegistro;
    private String indicadorItemChecklist;
    
  
    
    // Getters e Setters
}
