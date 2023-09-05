package com.igor.minhasfinancas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.minhasfinancas.model.entity.ChecklistCustomizado;

public interface ChecklistCustomizadoRepository extends JpaRepository<ChecklistCustomizado, UUID> {
}
