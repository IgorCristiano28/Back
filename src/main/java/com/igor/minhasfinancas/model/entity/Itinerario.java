package com.igor.minhasfinancas.model.entity;

import javax.persistence.*;

import com.igor.minhasfinancas.model.enums.StatusLancamento;
import com.igor.minhasfinancas.model.enums.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itinerario", schema = "financas")
public class Itinerario {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo_checklist_itinerario")
    private UUID codigoChecklistItinerario;

    @Column(name = "codigo_sigla_pais_origem")
    private String codigoSiglaPaisOrigem;

    @Column(name = "codigo_sigla_pais_destino")
    private String codigoSiglaPaisDestino;

    @Column(name = "codigo_localidade_diretorio_nacional_endereco_origem")
    private String codigoLocalidadeDiretorioNacionalEnderecoOrigem;

    @Column(name = "codigo_localidade_diretorio_nacional_endereco_destino")
    private String codigoLocalidadeDiretorioNacionalEnderecoDestino;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_inclusao_registro")
    private Date dataHoraInclusaoRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_manutencao_registro")
    private Date dataHoraManutencaoRegistro;

    @ManyToOne
    @JoinColumn(name = "codigo_viagem_checklist")
    private ViagemChecklistCliente viagemChecklist;

    // Construtores, getters e setters
}
