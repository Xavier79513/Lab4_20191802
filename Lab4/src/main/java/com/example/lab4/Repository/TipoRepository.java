package com.example.lab4.Repository;

import com.example.lab4.Entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
    Tipo findByNombre(String nombre);
}