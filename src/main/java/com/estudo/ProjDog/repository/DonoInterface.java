package com.estudo.ProjDog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.ProjDog.entity.Dono;

public interface DonoInterface extends JpaRepository<Dono, Long> {
    Optional<List<Dono>> findByNameContainingIgnoreCase(String nome);
    
    Optional<Dono> findByNameIgnoreCase(String nome);
}