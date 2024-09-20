package com.example.lab4.Repository;

import com.example.lab4.Entity.Ocasion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcasionRepository extends JpaRepository<Ocasion, Long> {
    Ocasion findByNombre(String nombre);
}