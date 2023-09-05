package com.igor.minhasfinancas.model.entity;

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
@Table(name = "checklist", schema = "financas")
public class Checklist {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_checklist")
    private UUID codigoChecklist;

    @Column(name = "codigo_externo_categoria")
    private String codigoExternoCategoria;

    @Column(name = "codigo_externo_item_checklist")
    private String codigoExternoItemChecklist;

    @Column(name = "data_hora_inclusao_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInclusaoRegistro;

    @Column(name = "data_hora_manutencao_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraManutencaoRegistro;

    @Column(name = "indicador_item_checklist")
    private String indicadorItemChecklist;

    @ManyToOne
    @JoinColumn(name = "codigo_viagem_checklist")
    private ViagemChecklistCliente viagemChecklist;


    // Getters e Setters
}
