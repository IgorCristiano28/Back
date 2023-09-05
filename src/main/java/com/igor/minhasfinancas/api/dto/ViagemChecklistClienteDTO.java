package com.igor.minhasfinancas.api.dto;

import java.security.Timestamp;
import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViagemChecklistClienteDTO {
	 	private String nomeViagemChecklist;
	    private Date dataViagemChecklistIda;
	    private Date dataViagemChecklistVolta;
	    private String codigoIdentificacaoPessoa;
	    
	    @JsonProperty("tipoViagem")
	    private UUID tipoViagemId;
}
