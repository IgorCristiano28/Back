package com.igor.backend.model.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checklistCustomizado", schema = "financas")
public class ChecklistCustomizado {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "codigo_checklist_customizado")
	    private UUID codigoChecklistCustomizado;

	    private String descricaoChecklistCustomizado;

	    @Column(name = "indicador_item_checklist_customizado")
	    private String indicadorItemChecklistCustomizado;

	    @Column(name = "data_hora_inclusao_registro")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date dataHoraInclusaoRegistro;

	    @Column(name = "data_hora_manutencao_registro")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date dataHoraManutencaoRegistro;

	    @ManyToOne
	    @JoinColumn(name = "codigo_viagem_checklist")
	    private ViagemChecklistCliente viagemChecklist;


    // Getters e Setters
}
