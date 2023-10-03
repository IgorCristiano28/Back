package com.igor.minhasfinancas.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.igor.minhasfinancas.model.enums.StatusLancamento;
import com.igor.minhasfinancas.model.enums.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viagem", schema = "financas")
public class ViagemChecklistCliente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_viagem_checklist", columnDefinition = "uuid")
    private UUID codigoViagemChecklist;

    @Column(name = "nome_viagem_checklist")
    private String nomeViagemChecklist;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_viagem_checklist_ida")
    private Date dataViagemChecklistIda;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_viagem_checklist_volta")
    private Date dataViagemChecklistVolta;
    
    @Column(name = "codigo_identificacao_pessoa")
    private String codigoIdentificacaoPessoa;

    // Relacionamento com TipoViagemChecklistCliente
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora essas propriedades na serialização
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_viagem_id")
    private TipoViagemChecklistCliente tipoViagem;

    // Relacionamento com Itinerario
    @OneToMany(mappedBy = "codigoViagemChecklist", cascade = CascadeType.ALL)
    private List<Itinerario> itinerarios;

    // Relacionamento com Checklist
    @OneToMany(mappedBy = "viagemChecklist", cascade = CascadeType.ALL)
    private List<Checklist> checklists;

    // Relacionamento com ChecklistCustomizado
    @OneToMany(mappedBy = "viagemChecklist", cascade = CascadeType.ALL)
    private List<ChecklistCustomizado> checklistCustomizados;

    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_inclusao_registro")
    private LocalDateTime dataHoraInclusaoRegistro;

    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_manutencao_registro")
    private LocalDateTime dataHoraManutencaoRegistro;
}
