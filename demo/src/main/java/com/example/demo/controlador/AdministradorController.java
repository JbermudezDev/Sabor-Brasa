package com.example.demo.controlador;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
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
    private final AdministradorService administradorService;
    private final ProductoRepository productoRepository; // Inyección de ProductoRepository

    public AdministradorController(AdministradorService administradorService, ProductoRepository productoRepository) {
        this.administradorService = administradorService;
        this.productoRepository = productoRepository;
    }

    
    
    @GetMapping("/Administrador")
    public String listarProductos(Model model) {
        List<Producto> productos = productoRepository.findAll(); // Obtiene los productos de la BD
        model.addAttribute("productos", productos);
        return "Administrador";  // Renderiza Administrador.html
    }
    
    
    

        
    public List<Administrador> listarTodos() {
        return administradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> buscarPorId(@PathVariable Long id) {
        return administradorService.buscarPorId(id);
    }

    @PostMapping
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorService.guardar(administrador);
    }

    @PutMapping("/{id}")
    public Administrador actualizar(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorService.guardar(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        administradorService.eliminarPorId(id);
    }
}