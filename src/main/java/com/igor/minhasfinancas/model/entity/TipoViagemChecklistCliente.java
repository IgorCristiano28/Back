package com.igor.minhasfinancas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_viagem", schema = "financas")
public class TipoViagemChecklistCliente {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "codigo_tipo_viagem")
	 private UUID codigoTipoViagem;

	 @JsonFormat(shape = JsonFormat.Shape.STRING)
	 @Column(name = "nome_tipo_viagem")
	 private String nomeTipoViagem;

    // Construtores, getters e setters
}
