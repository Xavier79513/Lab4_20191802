package com.example.lab4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.lab4.Repository.FlorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlorController {
    @Autowired
    private FlorRepository florRepository;

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        model.addAttribute("flores", florRepository.findAll());
        return "catalogo";
    }

}