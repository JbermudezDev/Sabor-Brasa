package com.example.demo.controlador;

import com.example.demo.entidades.SeleccionarProductos;
import com.example.demo.servicio.SeleccionarProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seleccion")
@CrossOrigin(origins = "http://localhost:4200")
public class SeleccionarProductosController {

    @Autowired
    private SeleccionarProductosService seleccionarProductosService;

    @PostMapping("/agregar")
    public ResponseEntity<SeleccionarProductos> agregarProducto(
            @RequestParam Long clienteId,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {

        SeleccionarProductos nuevo = seleccionarProductosService.agregarProductoACarrito(clienteId, productoId, cantidad);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/carrito/{carritoId}")
    public List<SeleccionarProductos> listarPorCarrito(@PathVariable Integer carritoId) {
        return seleccionarProductosService.listarPorCarrito(carritoId);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        seleccionarProductosService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }
}
