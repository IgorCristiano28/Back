package com.igor.minhasfinancas.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ItinerarioDTO {	 
	private UUID codigoChecklistItinerario;
    private String codigoSiglaPaisOrigem;
    private String codigoSiglaPaisDestino;
    private String codigoLocalidadeDiretorioNacionalEnderecoOrigem;
    private String codigoLocalidadeDiretorioNacionalEnderecoDestino;
    private LocalDateTime dataHoraInclusaoRegistro;
    private LocalDateTime dataHoraManutencaoRegistro;
	public UUID getCodigoChecklistItinerario() {
		return codigoChecklistItinerario;
	}
	public void setCodigoChecklistItinerario(UUID codigoChecklistItinerario) {
		this.codigoChecklistItinerario = codigoChecklistItinerario;
	}
	public String getCodigoSiglaPaisOrigem() {
		return codigoSiglaPaisOrigem;
	}
	public void setCodigoSiglaPaisOrigem(String codigoSiglaPaisOrigem) {
		this.codigoSiglaPaisOrigem = codigoSiglaPaisOrigem;
	}
	public String getCodigoSiglaPaisDestino() {
		return codigoSiglaPaisDestino;
	}
	public void setCodigoSiglaPaisDestino(String codigoSiglaPaisDestino) {
		this.codigoSiglaPaisDestino = codigoSiglaPaisDestino;
	}
	public String getCodigoLocalidadeDiretorioNacionalEnderecoOrigem() {
		return codigoLocalidadeDiretorioNacionalEnderecoOrigem;
	}
	public void setCodigoLocalidadeDiretorioNacionalEnderecoOrigem(String codigoLocalidadeDiretorioNacionalEnderecoOrigem) {
		this.codigoLocalidadeDiretorioNacionalEnderecoOrigem = codigoLocalidadeDiretorioNacionalEnderecoOrigem;
	}
	public String getCodigoLocalidadeDiretorioNacionalEnderecoDestino() {
		return codigoLocalidadeDiretorioNacionalEnderecoDestino;
	}
	public void setCodigoLocalidadeDiretorioNacionalEnderecoDestino(
			String codigoLocalidadeDiretorioNacionalEnderecoDestino) {
		this.codigoLocalidadeDiretorioNacionalEnderecoDestino = codigoLocalidadeDiretorioNacionalEnderecoDestino;
	}
	public LocalDateTime getDataHoraInclusaoRegistro() {
		return dataHoraInclusaoRegistro;
	}
	public void setDataHoraInclusaoRegistro(LocalDateTime dataHoraInclusaoRegistro) {
		this.dataHoraInclusaoRegistro = dataHoraInclusaoRegistro;
	}
	public LocalDateTime getDataHoraManutencaoRegistro() {
		return dataHoraManutencaoRegistro;
	}
	public void setDataHoraManutencaoRegistro(LocalDateTime dataHoraManutencaoRegistro) {
		this.dataHoraManutencaoRegistro = dataHoraManutencaoRegistro;
	}
    
    

}
