package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import com.example.demo.servicio.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public List<Producto> mostrarProductos(Model model) {
    return productoService.searchAll();
  }

  // Ver detalles de un producto

  @GetMapping("/find/{id}")
  public Producto veProducto(@PathVariable("id") Long id) {
    return productoService.findById(id).orElse(null);
  }

  // Formulario para agregar un producto
  @GetMapping("/agregar")
  public String mostrarFormularioAgregarProducto(Model model) {
    model.addAttribute("producto", new Producto());
    return "AgregarProducto";
  }

  // Guardar un producto
  @PostMapping("/add")
  public void agregarProducto(@RequestBody Producto producto) {
    productoService.add(producto);
  }

  // Eliminar un producto

  @DeleteMapping("/delete/{id}") // Cambiado a /delete/{id} para evitar conflictos con el método eliminarCliente
  public void eliminarProducto(@PathVariable("id") Long id) {
    productoService.deleteById(id);
  }

  // Formulario de edición de un producto

  @PutMapping("/update/{id}") // Cambiado a /update/{id} para evitar conflictos con el método modificarCliente
  public void modificarProducto(@RequestBody Producto producto) {
    productoService.update(producto);
  }

  // Guardar cambios de un producto
  @PostMapping("/update/{id}")
  public String modificarProducto(
    @PathVariable("id") Long id,
    @ModelAttribute Producto producto,
    @RequestParam(required = false) List<Long> adicionales,
    Model model
  ) {
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