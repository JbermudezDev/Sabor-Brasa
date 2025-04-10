package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import com.example.demo.servicio.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AdicionalService adicionalService;

    // Listar productos
    @GetMapping("/all")
public ResponseEntity<?> mostrarProductos() {
    try {
        // Obtener todos los productos con sus adicionales
        List<Producto> productos = productoService.searchAll();
        List<Adicional> adicionales = adicionalService.findAll(); // Obtener todos los adicionales disponibles

        // Crear una respuesta con los productos y los adicionales
        return ResponseEntity.ok(new Object() {
            public List<Producto> productosList = productos;
            public List<Adicional> adicionalesList = adicionales;
        });
    } catch (Exception e) {
        // Manejar errores y retornar un código 500 (Internal Server Error)
        e.printStackTrace(); // Imprimir el error en los logs para depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los productos y adicionales");
    }
}

    // Ver detalles de un producto
    @GetMapping("/find/{id}")
    public Producto verProducto(@PathVariable("id") Long id) {
        return productoService.findById(id).orElse(null);
    }

    // Formulario para agregar un producto
   // Formulario para agregar un producto
   @GetMapping("/agregar")
   public ResponseEntity<?> mostrarFormularioAgregarProducto() {
       try {
           Producto producto = new Producto(); // Crear un nuevo producto vacío
           List<Adicional> adicionales = adicionalService.findAll(); // Obtener todos los adicionales disponibles
   
           // Crear una respuesta con el producto y los adicionales
           return ResponseEntity.ok(new Object() {
               public Producto productoObj = producto;
               public List<Adicional> adicionalesList = adicionales;
           });
       } catch (Exception e) {
           e.printStackTrace(); // Imprimir el error en los logs para depuración
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el formulario");
       }
   }

    // Guardar un producto
    @PostMapping("/add")
public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
    try {
        // Asociar adicionales al producto si existen
        if (producto.getAdicionales() != null && !producto.getAdicionales().isEmpty()) {
            List<Adicional> adicionalesSeleccionados = adicionalService.findByIds(
                producto.getAdicionales().stream().map(Adicional::getId).toList()
            );
            producto.setAdicionales(adicionalesSeleccionados);
        } else {
            producto.setAdicionales(new ArrayList<>()); // Si no hay adicionales, inicializar como lista vacía
        }

        // Guardar el producto en la base de datos
        Producto nuevoProducto = productoService.add(producto);

        // Retornar el producto guardado con un código 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    } catch (Exception e) {
        // Manejar errores y retornar un código 500 (Internal Server Error)
        e.printStackTrace(); // Imprimir el error en los logs para depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
    // Eliminar un producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id) {
        try {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
        }
    }

    // Guardar cambios de un producto
    @PutMapping("/update/{id}")
public ResponseEntity<?> modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
    try {
        Optional<Producto> productoExistente = productoService.findById(id);
        if (productoExistente.isPresent()) {
            Producto prod = productoExistente.get();
            prod.setNombre(producto.getNombre());
            prod.setPrecio(producto.getPrecio());
            prod.setDescripcion(producto.getDescripcion());
            prod.setImagen(producto.getImagen());

            // Asociar adicionales
            if (producto.getAdicionales() != null && !producto.getAdicionales().isEmpty()) {
                List<Adicional> adicionalesSeleccionados = adicionalService.findByIds(
                    producto.getAdicionales().stream().map(Adicional::getId).toList()
                );
                prod.setAdicionales(adicionalesSeleccionados);
            } else {
                prod.setAdicionales(new ArrayList<>());
            }

            productoService.save(prod);
            return ResponseEntity.ok(prod); // Devuelve el producto actualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Imprimir el error en los logs para depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto");
    }
}
    // Menú con productos
    @GetMapping("/Menu")
    public ResponseEntity<?> mostrarMenu() {
        try {
            List<Producto> productos = productoService.obtenerTodos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el menú");
        }
    }

    // Ver información de un plato
    @GetMapping("/info/{id}")
    public ResponseEntity<?> mostrarInfoPlato(@PathVariable Long id) {
        Optional<Producto> productoOpt = productoService.findById(id);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            return ResponseEntity.ok(producto); // Devuelve el producto con sus adicionales en formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
}