package com.example.demo.controlador;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdministradorService;

import com.example.demo.repositorio.ProductoRepository;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/administradores")


public class AdministradorController {
   @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ProductoRepository productoRepository;

    
    //http://localhost:8090/administradores/administrador
    @GetMapping("/Administrador")
    public String listarProductos(Model model) {
        List<Producto> productos = productoRepository.findAll(); // Obtiene los productos de la BD
        model.addAttribute("productos", productos);
        return "Administrador";  // Renderiza Administrador.html
    }
    
    //http://localhost:8090/administradores/logout
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
    session.invalidate(); // Invalida la sesión actual
    return "Index"; // Redirige a la página de inicio (index)
}
    
    
    

        
  
   

    

   
}