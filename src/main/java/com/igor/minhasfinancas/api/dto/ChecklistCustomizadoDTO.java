package com.igor.minhasfinancas.api.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistCustomizadoDTO {
    private String descricaoChecklistCustomizado;
    private Date dataHoraInclusaoRegistro;
    private Date dataHoraManutencaoRegistro;
    
    
}
