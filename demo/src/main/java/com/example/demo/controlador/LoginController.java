package com.example.demo.controlador;


import com.example.demo.entidades.Administrador;
import com.example.demo.servicio.AdministradorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "IniciarSesionAdministrador"; 
    }

    @PostMapping("/login")
    public String loginAdministrador(@RequestParam String email, 
                                     @RequestParam String password, 
                                     Model model, 
                                     HttpSession session) {
        Administrador admin = administradorService.autenticar(email, password);
    
        if (admin != null) {
            session.setAttribute("adminLogueado", admin);
            return "redirect:/Administrador"; // Asegura que se redirija correctamente
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "IniciarSesionAdministrador"; 
        }
    }

    @GetMapping("/Administrador")
    public String mostrarPaginaAdministrador() {
        return "Administrador"; 
    }
}
