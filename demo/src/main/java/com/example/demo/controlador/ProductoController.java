package com.example.demo.controlador;

import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorio.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/all")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.searchAll();
        model.addAttribute("productos", productos);
        return "ListadoProductos";
    }

    @GetMapping("/view/{id}")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        Optional<Producto> producto = productoRepository.findById(id);
        
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "VerProducto"; // Asegúrate de que este es el nombre correcto de la vista
        } else {
            return "redirect:/productos/all"; // Redirige si el producto no existe
        }
    }

    @PostMapping("/add")
    public String agregarProducto(@ModelAttribute Producto producto) {
        productoService.add(producto);
        return "redirect:/productos/all";
    }

    @PostMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.deleteById(id);
        return "redirect:/productos/all";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "EditarProducto";
        } else {
            return "redirect:/productos/all";
        }
    }

    @PostMapping("/update/{id}")
    public String modificarProducto(@ModelAttribute Producto producto, @PathVariable("id") Long id) {
        producto.setId(id);
        productoService.update(producto);
        return "redirect:/productos/all";
    }
}
