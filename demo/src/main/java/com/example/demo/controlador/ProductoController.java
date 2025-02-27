package com.example.demo.controlador;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Producto;
import com.example.demo.servicio.ProductoService;
import com.example.demo.repositorio.ProductoRepository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll(); // Devuelve los productos en formato JSON
    }

    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoService.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminarPorId(id);
    }
}