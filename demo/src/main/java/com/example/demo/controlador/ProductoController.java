package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import com.example.demo.servicio.ProductoService;
import com.example.demo.entidades.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AdicionalService adicionalService;

    @GetMapping("/all")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.searchAll();
        model.addAttribute("productos", productos);
        return "ListadoProductos";
    }

    @GetMapping("/view/{id}")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "VerProducto";
        } else {
            return "redirect:/productos/all";
        }
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "AgregarProducto";
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
            List<Adicional> adicionales = adicionalService.searchAll();
            model.addAttribute("producto", producto.get());
            model.addAttribute("adicionales", adicionales);
            return "EditarProducto";
        } else {
            return "redirect:/productos/all";
        }
    }

    @PostMapping("/update/{id}")
    public String modificarProducto(@PathVariable("id") Long id, @ModelAttribute Producto producto, @RequestParam List<Long> adicionales) {
        Optional<Producto> productoExistente = productoService.findById(id);
        if (productoExistente.isPresent()) {
            Producto prod = productoExistente.get();
            
            // Asegurar que el ID se mantenga
            prod.setId(id);
            
            // Actualizar solo los campos permitidos
            prod.setNombre(producto.getNombre());
            prod.setPrecio(producto.getPrecio());
            prod.setDescripcion(producto.getDescripcion());
            prod.setImagen(producto.getImagen());
    
            // Actualizar adicionales
            List<Adicional> adicionalesSeleccionados = adicionalService.findByIds(adicionales);
            prod.setAdicionales(adicionalesSeleccionados);
    
            // Guardar los cambios
            productoService.save(prod);
        }
        return "redirect:/productos/view/" + id;
    }
    

    @GetMapping("/Menu")
    public String mostrarMenu(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        model.addAttribute("productos", productos);
        return "Menu";
    }

    @GetMapping("/InfoPlato/{id}")
public String mostrarInfoPlato(@PathVariable Long id, Model model) {
    Optional<Producto> productoOpt = productoService.obtenerPorId(id);
    
    if (productoOpt.isPresent()) {
        Producto producto = productoOpt.get();
        model.addAttribute("producto", producto);

        // Si el producto NO es una bebida, carga la lista de bebidas
        if (producto.getCategoria() != Categoria.BEBIDA) {
            List<Producto> bebidas = productoService.obtenerPorCategoria(Categoria.BEBIDA);
            model.addAttribute("bebidas", bebidas);
        }

        return "InfoPlato";
    } else {
        return "redirect:/productos/Menu"; // Si no encuentra el producto, regresa al menú
    }

    }
}