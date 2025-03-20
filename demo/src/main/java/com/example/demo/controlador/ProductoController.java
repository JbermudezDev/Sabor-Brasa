package com.example.demo.controlador;

import com.example.demo.entidades.Producto;
import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Categoria;
import com.example.demo.servicio.ProductoService;
import com.example.demo.servicio.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AdicionalService adicionalService;

    // Listar productos
    @GetMapping("/all")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.searchAll();
        model.addAttribute("productos", productos);
        return "ListadoProductos";
    }

    // Ver detalles de un producto
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

    // Formulario para agregar un producto
    @GetMapping("/agregar")
    public String mostrarFormularioAgregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "AgregarProducto";
    }

    // Guardar un producto
    @PostMapping("/add")
    public String agregarProducto(@ModelAttribute Producto producto) {
        productoService.add(producto);
        return "redirect:/productos/all";
    }

    // Eliminar un producto
    @PostMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.deleteById(id);
        return "redirect:/productos/all";
    }

    // Formulario de edición de un producto
    
    // Formulario de edición de un producto
    @GetMapping("/update/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            List<Adicional> adicionales = adicionalService.findAll(); // Cargar todos los adicionales
            model.addAttribute("producto", producto.get());
            model.addAttribute("adicionales", adicionales);
            return "EditarProducto"; // Asegurar que la vista recibe la lista de adicionales
        } else {
            return "redirect:/productos/all"; // Si no existe, redirigir al listado
        }
    }
    

    // Guardar cambios de un producto
   @PostMapping("/update/{id}")
public String modificarProducto(@PathVariable("id") Long id, @ModelAttribute Producto producto, 
                                @RequestParam(required = false) List<Long> adicionales, Model model) {
    Optional<Producto> productoExistente = productoService.findById(id);
    if (productoExistente.isPresent()) {
        Producto prod = productoExistente.get();
        prod.setNombre(producto.getNombre());
        prod.setPrecio(producto.getPrecio());
        prod.setDescripcion(producto.getDescripcion());
        prod.setImagen(producto.getImagen());

        // Si la lista de adicionales no es nula, la actualiza; si es nula, la deja vacía
        List<Adicional> adicionalesSeleccionados = (adicionales != null) 
            ? adicionalService.findByIds(adicionales) 
            : new ArrayList<>();
        prod.setAdicionales(adicionalesSeleccionados);

        // Guardar los cambios
        productoService.save(prod);
        
        return "redirect:/productos/view/" + id;
    } else {
        model.addAttribute("error", "El producto no existe.");
        model.addAttribute("producto", producto);
        model.addAttribute("adicionales", adicionalService.findAll()); // Asegurar que la lista se envíe a la vista
        return "EditarProducto";
    }
}


    // Menú con productos
    @GetMapping("/Menu")
    public String mostrarMenu(Model model) {
        List<Producto> productos = productoService.obtenerTodos();
        model.addAttribute("productos", productos);
        return "Menu";
    }
    @GetMapping("/InfoPlato/{id}")
    public String mostrarInfoPlato(@PathVariable Long id, Model model) {
        Optional<Producto> productoOpt = productoService.findById(id);
        
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            model.addAttribute("producto", producto);

            // Solo pasamos los adicionales que fueron seleccionados por el administrador
            model.addAttribute("adicionales", producto.getAdicionales());

            return "InfoPlato";
        } else {
            return "redirect:/productos/Menu";
        }
    }

    // Ver información de un plato
   
}