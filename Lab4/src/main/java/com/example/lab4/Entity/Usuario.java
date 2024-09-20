package com.example.lab4.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private int puntuacion;

    // Constructores, getters y setters
    public Usuario() {}

    public Usuario(String nickname, int puntuacion) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;
    }

}