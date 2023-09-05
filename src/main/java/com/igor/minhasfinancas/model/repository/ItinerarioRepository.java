package com.igor.minhasfinancas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.minhasfinancas.model.entity.Itinerario;

public interface ItinerarioRepository extends JpaRepository<Itinerario, UUID> {
	
}
