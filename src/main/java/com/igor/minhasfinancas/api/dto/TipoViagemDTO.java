package com.igor.minhasfinancas.api.dto;

import java.util.UUID;

public class TipoViagemDTO {
	private UUID codigoTipoViagem;
    private String nomeTipoViagem;
	public UUID getCodigoTipoViagem() {
		return codigoTipoViagem;
	}
	public void setCodigoTipoViagem(UUID codigoTipoViagem) {
		this.codigoTipoViagem = codigoTipoViagem;
	}
	public String getNomeTipoViagem() {
		return nomeTipoViagem;
	}
	public void setNomeTipoViagem(String nomeTipoViagem) {
		this.nomeTipoViagem = nomeTipoViagem;
	}
    
    

}
