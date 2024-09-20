package com.example.lab4.Controllers;

import com.example.lab4.Entity.Flor;
import com.example.lab4.Repository.ColorRepository;
import com.example.lab4.Repository.FlorRepository;
import com.example.lab4.Entity.Color;
import com.example.lab4.Entity.Ocasion;
import com.example.lab4.Entity.Tipo;
import com.example.lab4.Repository.OcasionRepository;
import com.example.lab4.Repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class HomeController {

    @Autowired
    private FlorRepository florRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private OcasionRepository ocasionRepository;

    @GetMapping("/index1")
    public String viewindex() {
        return "index";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(@RequestParam(required = false) String color,
                                  @RequestParam(required = false) String tipo,
                                  @RequestParam(required = false) String ocasion,
                                  Model model) {
        List<Flor> flores;

        // Intentar buscar los objetos por nombre si están presentes
        Color colorObj = (color != null && !color.isEmpty()) ? colorRepository.findByNombre(color) : null;
        Tipo tipoObj = (tipo != null && !tipo.isEmpty()) ? tipoRepository.findByNombre(tipo) : null;
        Ocasion ocasionObj = (ocasion != null && !ocasion.isEmpty()) ? ocasionRepository.findByNombre(ocasion) : null;

        // Buscar las flores según los filtros aplicados
        if (colorObj != null || tipoObj != null || ocasionObj != null) {
            flores = florRepository.findByFilters(colorObj, tipoObj, ocasionObj);
        } else {
            // Si no hay filtros, mostrar todas las flores
            flores = florRepository.findAll();
        }

        model.addAttribute("flores", flores);
        model.addAttribute("totalFlores", flores.size()); // Agregar número total de flores al modelo
        return "Catalogo";
    }
}