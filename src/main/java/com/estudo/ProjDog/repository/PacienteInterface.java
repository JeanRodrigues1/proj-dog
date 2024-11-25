package com.estudo.ProjDog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.ProjDog.entity.Paciente;

public interface PacienteInterface extends JpaRepository<Paciente, Long> {
    Optional<List<Paciente>> findByNameContainingIgnoreCase(String nome);
    
    Optional<Paciente> findByNameIgnoreCase(String nome);
}