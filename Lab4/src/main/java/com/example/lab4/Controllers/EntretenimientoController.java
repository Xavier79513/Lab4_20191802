package com.example.lab4.Controllers;

import com.example.lab4.Entity.Flor;
import com.example.lab4.Entity.Usuario;
import com.example.lab4.Repository.FlorRepository;
import com.example.lab4.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/entretenimiento")
public class EntretenimientoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FlorRepository florRepository;

    // Variable para llevar el puntaje
    private int puntajeActual = 0;

    // Variable para almacenar el nombre del usuario
    private String nombreUsuario;

    // Lista de flores mezclada
    private List<Flor> floresMezcladas;

    @GetMapping("/entretenimientov")
    public String verEntretenimiento(Model model) {
        // Obtener las flores del catálogo
        // Mezclar las flores para el juego
        floresMezcladas = florRepository.findAll();
        Collections.shuffle(floresMezcladas);
        model.addAttribute("flores", floresMezcladas);

        // Obtener los jugadores ordenados por puntuación
        List<Usuario> jugadores = usuarioRepository.findAllByOrderByPuntuacionDesc();
        model.addAttribute("jugadores", jugadores);

        return "Entretenimiento";
    }

    @PostMapping("/guardarPuntuacion")
    public String guardarPuntuacion(@RequestParam("usuario") String usuario) {
        // Guardar el nombre del usuario
        this.nombreUsuario = usuario;
        // Reiniciar el puntaje al comenzar
        this.puntajeActual = 0;

        return "redirect:/entretenimiento/jugar";
    }

    @GetMapping("/jugar")
    public String iniciarJuego(Model model) {
        // Mostrar las flores mezcladas en el tablero
        model.addAttribute("flores", floresMezcladas);
        model.addAttribute("puntaje", puntajeActual);

        return "Entretenimiento"; // Nueva vista para el juego
    }

    @PostMapping("/voltearCarta")
    public String voltearCarta(@RequestParam("indice") int indice, @RequestParam("indiceAnterior") Integer indiceAnterior, Model model) {
        Flor cartaSeleccionada = floresMezcladas.get(indice);
        Flor cartaAnterior = indiceAnterior != null ? floresMezcladas.get(indiceAnterior) : null;

        if (cartaAnterior != null && cartaSeleccionada.getColor().equals(cartaAnterior.getColor())) {
            // Las cartas hacen match
            puntajeActual++;
        } else if (cartaAnterior != null) {
            // No hacen match
            if (puntajeActual > 0) puntajeActual--;
        }

        model.addAttribute("flores", floresMezcladas);
        model.addAttribute("puntaje", puntajeActual);

        // Si todas las cartas están emparejadas, guardar la puntuación
        if (puntajeActual == floresMezcladas.size() / 2) {
            Usuario usuario = new Usuario(nombreUsuario, puntajeActual);
            usuarioRepository.save(usuario);
            return "redirect:/entretenimiento/finJuego";
        }

        return "Entretenimiento"; // Volver al juego hasta emparejar todas
    }

    @GetMapping("/finJuego")
    public String finJuego(Model model) {
        model.addAttribute("puntaje", puntajeActual);
        model.addAttribute("nombreUsuario", nombreUsuario);
        return "FinJuego"; // Nueva vista para mostrar el final
    }

}