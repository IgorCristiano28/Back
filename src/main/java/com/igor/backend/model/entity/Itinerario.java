package com.igor.backend.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igor.backend.model.enums.StatusLancamento;
import com.igor.backend.model.enums.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_inclusao_registro")
    private LocalDateTime dataHoraInclusaoRegistro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora_manutencao_registro")
    private LocalDateTime dataHoraManutencaoRegistro;

    //@ManyToOne
    //@JoinColumn(name = "codigo_viagem_checklist")
    @Column(name = "codigo_viagem_checklist", columnDefinition = "uuid")
    private UUID codigoViagemChecklist;

	

    // Construtores, getters e setters
}
