package com.igor.backend.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.backend.model.entity.ChecklistCustomizado;

public interface ChecklistCustomizadoRepository extends JpaRepository<ChecklistCustomizado, UUID> {
}
