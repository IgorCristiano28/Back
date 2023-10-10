package com.igor.minhasfinancas.api.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ViagemDTO {
	private UUID codigoViagemChecklist;
    private String nomeViagemChecklist;
    private Date dataViagemChecklistIda;
    private Date dataViagemChecklistVolta;
    private String codigoIdentificacaoPessoa;
    private TipoViagemDTO tipoViagem;
    private List<ItinerarioDTO> itinerarios;
    private List<ChecklistDTO> checklists;
    
	public UUID getCodigoViagemChecklist() {
		return codigoViagemChecklist;
	}
	public void setCodigoViagemChecklist(UUID codigoViagemChecklist) {
		this.codigoViagemChecklist = codigoViagemChecklist;
	}
	public String getNomeViagemChecklist() {
		return nomeViagemChecklist;
	}
	public void setNomeViagemChecklist(String nomeViagemChecklist) {
		this.nomeViagemChecklist = nomeViagemChecklist;
	}
	public Date getDataViagemChecklistIda() {
		return dataViagemChecklistIda;
	}
	public void setDataViagemChecklistIda(Date dataViagemChecklistIda) {
		this.dataViagemChecklistIda = dataViagemChecklistIda;
	}
	public Date getDataViagemChecklistVolta() {
		return dataViagemChecklistVolta;
	}
	public void setDataViagemChecklistVolta(Date dataViagemChecklistVolta) {
		this.dataViagemChecklistVolta = dataViagemChecklistVolta;
	}
	public String getCodigoIdentificacaoPessoa() {
		return codigoIdentificacaoPessoa;
	}
	public void setCodigoIdentificacaoPessoa(String codigoIdentificacaoPessoa) {
		this.codigoIdentificacaoPessoa = codigoIdentificacaoPessoa;
	}
	public TipoViagemDTO getTipoViagem() {
		return tipoViagem;
	}
	public void setTipoViagem(TipoViagemDTO tipoViagem) {
		this.tipoViagem = tipoViagem;
	}
	public List<ItinerarioDTO> getItinerarios() {
		return itinerarios;
	}
	public void setItinerarios(List<ItinerarioDTO> itinerarios) {
		this.itinerarios = itinerarios;
	}
	public List<ChecklistDTO> getChecklists() {
		return checklists;
	}
	public void setChecklists(List<ChecklistDTO> checklists) {
		this.checklists = checklists;
	}
}
