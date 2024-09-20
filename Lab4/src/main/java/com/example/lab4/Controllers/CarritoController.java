package com.example.lab4.Controllers;

import com.example.lab4.Entity.Flor;
import com.example.lab4.Repository.FlorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private FlorRepository florRepository;

    // Carrito a nivel de la clase para manejar un solo usuario
    private final List<Flor> carrito = new ArrayList<>();

    // Ver la vista del carrito
    @GetMapping("/carritov")
    public String viewCarrito(Model model) {
        model.addAttribute("carrito", carrito);
        return "Carrito";
    }

    // Agregar una flor al carrito
    @GetMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, Model model) {
        florRepository.findById(id).ifPresent(carrito::add);
        model.addAttribute("carrito", carrito);
        return "redirect:/principal/catalogo";
    }

    // Quitar una flor del carrito
    @GetMapping("/quitar/{id}")
    public String quitarDelCarrito(@PathVariable Long id, Model model) {
        carrito.removeIf(flor -> flor.getId().equals(id));
        model.addAttribute("carrito", carrito);
        return "redirect:/principal/catalogo";
    }

    // Comprar una flor y hacer un borrado lógico
    @GetMapping("/comprar/{id}")
    public String comprarFlor(@PathVariable Long id, Model model) {
        florRepository.findById(id).ifPresent(flor -> {
            // Borrado lógico: eliminamos la flor del carrito
            carrito.remove(flor);
            model.addAttribute("florComprada", flor);
        });
        return "redirect:/carrito/confirmacion";
    }

    // Confirmación de la compra
    @GetMapping("/confirmacion")
    public String viewConfirmacion(Model model) {
        return "Confirmacion";
    }
}