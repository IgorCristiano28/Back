package com.igor.minhasfinancas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.minhasfinancas.model.entity.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, UUID> {
	
}