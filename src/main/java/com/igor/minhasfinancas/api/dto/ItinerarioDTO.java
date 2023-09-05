package com.igor.minhasfinancas.api.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItinerarioDTO {
	 private String codigoSiglaPaisOrigem;
	 private String codigoSiglaPaisDestino;
	 private String codigoLocalidadeDiretorioNacionalEnderecoOrigem;
	 private String codigoLocalidadeDiretorioNacionalEnderecoDestino;
	 private Date dataHoraInclusaoRegistro;
	 private Date dataHoraManutencaoRegistro;


}
