package com.example.demo.controlador;

import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Cliente;
import com.example.demo.servicio.AdministradorService;
import com.example.demo.servicio.ClienteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ClienteService clienteService;

    // Muestra formulario de login del cliente
    @GetMapping("/loginCliente")
    public String mostrarFormularioLoginCliente() {
        return "InicioSesion"; 
    }

    // Autenticación del Cliente
    @PostMapping("/loginCliente")
    public String loginCliente(@RequestParam String email, 
                               @RequestParam String password, 
                               Model model, 
                               HttpSession session) {
        Cliente cliente = clienteService.autenticar(email, password);

        if (cliente != null) {
            session.setAttribute("clienteLogueado", cliente);
            return "redirect:/Cliente/" + cliente.getId(); // Redirige con el ID en la URL
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "InicioSesion"; 
        }
    }
    

    // Página del Cliente (requiere autenticación)
    @GetMapping("/Cliente/{id}")
    public String mostrarPaginaCliente(@PathVariable Long id, Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null || !cliente.getId().equals(id)) {
            return "redirect:/loginCliente"; // Redirige al login si no está autenticado
        }

        model.addAttribute("cliente", cliente);
        return "Cliente"; 
    }

    // Muestra formulario de login del administrador
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "IniciarSesionAdministrador"; 
    }

    // Autenticación del Administrador
    @PostMapping("/login")
    public String loginAdministrador(@RequestParam String email, 
                                     @RequestParam String password, 
                                     Model model, 
                                     HttpSession session) {
        Administrador admin = administradorService.autenticar(email, password);

        if (admin != null) {
            session.setAttribute("adminLogueado", admin);
            return "redirect:/Administrador"; 
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "IniciarSesionAdministrador"; 
        }
    }

    // Página del Administrador
    @GetMapping("/Administrador")
    public String mostrarPaginaAdministrador() {
        return "Administrador"; 
    }
}
