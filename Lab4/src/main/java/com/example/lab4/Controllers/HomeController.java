package com.example.lab4.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class HomeController {
    @GetMapping("/index1")
    public String viewindex(){return "index";}

    @GetMapping("/catalogo")
    public String viewcatalogo(){return "Catalogo";}

    @GetMapping("/entretenimiento")
    public String viewentre(){return "Entretenimiento";}

}
