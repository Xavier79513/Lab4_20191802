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

import java.util.List;

@Controller
@RequestMapping("/entretenimiento")
public class EntretenimientoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FlorRepository florRepository;

    @GetMapping("/entretenimientov")
    public String verEntretenimiento(Model model) {
        // Obtener las flores del catálogo
        List<Flor> flores = florRepository.findAll();
        model.addAttribute("flores", flores);

        // Obtener los jugadores ordenados por puntuación
        List<Usuario> jugadores = usuarioRepository.findAllByOrderByPuntuacionDesc();
        model.addAttribute("jugadores", jugadores);

        return "Entretenimiento";
    }

    @PostMapping("/guardarPuntuacion")
    public String guardarPuntuacion(@RequestParam("usuario") String usuario,
                                    @RequestParam("puntuacion") int puntuacion) {
        // Guardar la puntuación en la base de datos
        Usuario jugador = new Usuario(usuario, puntuacion);
        usuarioRepository.save(jugador);
        return "redirect:/entretenimiento/entretenimientov";
    }
}
