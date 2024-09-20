package com.example.lab4.Repository;

import com.example.lab4.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByNombre(String nombre);
}