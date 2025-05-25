package com.example.demo.controlador;


import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import com.example.demo.servicio.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AdicionalService adicionalService;

    //http://localhost:8090/productos/all
    // Listar productos
    @GetMapping("/all")
    public ResponseEntity<ProductosResponse> mostrarProductos() {
        try {
            // Obtén la lista de productos y adicionales desde los servicios
            List<Producto> productos = productoService.searchAll();
            List<Adicional> adicionales = adicionalService.findAll();

            // Crea una instancia de ProductosResponse
            ProductosResponse response = new ProductosResponse(productos, adicionales);

            // Devuelve la respuesta
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //http://localhost:8090/productos/find/id
    // Ver detalles de un producto
    @GetMapping("/find/{id}")
    public Producto verProducto(@PathVariable("id") Long id) {
        return productoService.findById(id).orElse(null);
    }

    //http://localhost:8090/productos/agregar
    // Formulario para agregar un producto
    @GetMapping("/agregar")
    public ResponseEntity<?> mostrarFormularioAgregarProducto() {
        try {
            Producto producto = new Producto();
            List<Adicional> adicionales = adicionalService.findAll();

            return ResponseEntity.ok(new Object() {
                public Producto productoObj = producto;
                public List<Adicional> adicionalesList = adicionales;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el formulario");
        }
    }

    //http://localhost:8090/productos/add
    // Crear nuevo producto
    @PostMapping("/add")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        try {
            if (producto.getAdicionales() != null && !producto.getAdicionales().isEmpty()) {
                List<Adicional> adicionalesSeleccionados = adicionalService.findByIds(
                        producto.getAdicionales().stream().map(Adicional::getId).toList());
                producto.setAdicionales(adicionalesSeleccionados);
            } else {
                producto.setAdicionales(new ArrayList<>());
            }

            Producto nuevoProducto = productoService.add(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //http://localhost:8090/productos/update/id
    // Actualizar producto existente
    @PutMapping("/update/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoExistente = productoService.findById(id);
            if (productoExistente.isPresent()) {
                Producto prod = productoExistente.get();

                // Actualiza los datos básicos del producto
                prod.setNombre(producto.getNombre());
                prod.setPrecio(producto.getPrecio());
                prod.setDescripcion(producto.getDescripcion());
                prod.setImagen(producto.getImagen());

                // Actualiza los adicionales
                if (producto.getAdicionales() != null && !producto.getAdicionales().isEmpty()) {
                    List<Adicional> adicionalesSeleccionados = adicionalService.findByIds(
                            producto.getAdicionales().stream().map(Adicional::getId).toList());
                    prod.setAdicionales(adicionalesSeleccionados);
                } else {
                    prod.setAdicionales(new ArrayList<>());
                }

                productoService.save(prod);
                return ResponseEntity.ok(prod);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el producto");
        }
    }

    //http://localhost:8090/productos/delete/id
    // Eliminar producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id) {
        try {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
        }
    }

    //http://localhost:8090/productos/menu
    // Menú
    @GetMapping("/menu")
    public ResponseEntity<List<Producto>> obtenerMenu() {
        try {
            List<Producto> productos = productoService.obtenerTodos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }


    //http://localhost:8090/productos/info/id
    // Info detallada de un plato
    @GetMapping("/info/{id}")
    public ResponseEntity<?> mostrarInfoPlato(@PathVariable Long id) {
    Optional<Producto> productoOpt = productoService.findById(id);

    if (productoOpt.isPresent()) {
        Producto producto = productoOpt.get();
        return ResponseEntity.ok(producto); // Devuelve el producto con sus adicionales
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
}

@GetMapping("/categorias-distribucion")
public ResponseEntity<Map<String, Integer>> obtenerDistribucionCategorias() {
    List<Producto> productos = productoService.obtenerTodos();
    Map<String, Integer> conteo = new TreeMap<>();

    for (Producto producto : productos) {
        String categoria = producto.getCategoria() != null ? producto.getCategoria().name() : "Sin categoría";
        conteo.put(categoria, conteo.getOrDefault(categoria, 0) + 1);
    }

    return ResponseEntity.ok(conteo);
}


}