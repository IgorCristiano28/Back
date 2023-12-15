package com.igor.backend.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.backend.model.entity.Itinerario;

public interface ItinerarioRepository extends JpaRepository<Itinerario, UUID> {
	
}
