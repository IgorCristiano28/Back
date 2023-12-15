package com.igor.backend.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_inclusao_registro")
    private LocalDateTime dataHoraInclusaoRegistro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_manutencao_registro")
    private LocalDateTime dataHoraManutencaoRegistro;

    @Column(name = "indicador_item_checklist")
    private Short  indicadorItemChecklist;

    @Column(name = "codigo_viagem_checklist", columnDefinition = "uuid")
    private UUID viagemChecklist;
    
    @Transient
    private List<ChecklistItem> checklistItems;
    
    @Transient
    private ChecklistItem checklistItem;
    
 // Construtor que inicializa a lista
    public void initializeChecklist() {
        this.checklistItems = new ArrayList<>();
    }
    
    
}
