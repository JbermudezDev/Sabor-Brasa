package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.example.demo.entidades.Producto;
import com.example.demo.servicio.ProductoService;


@Controller
public class MainController {

    @Autowired
    private ProductoService productoService;
    @GetMapping("/")
    public String main() {
        return "Index";
    }

    @GetMapping("/Perfilcliente")
    public String mainCliente() {
        return "PerfilCliente";
    }


    
    @GetMapping("/Menu")
    public String menu(Model model) {
        List<Producto> productos = productoService.obtenerTodos(); // Recupera los productos
        model.addAttribute("productos", productos);
        return "Menu"; // Retorna la vista del menú
    }
   
    
}