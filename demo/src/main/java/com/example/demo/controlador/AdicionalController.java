package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.servicio.AdicionalService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adicionales")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class AdicionalController {

    @Autowired
    private AdicionalService adicionalService;
    
    // Listar todos los adicionales
    //http://localhost:8090/adicionales/all
    @GetMapping("/all")
    public List<Adicional> listarAdicionales() {
        return adicionalService.findAll();
    }

    // Ver detalles de un adicional por ID
    //http://localhost:8090/adicionales/find/id
    @GetMapping("/find/{id}")
    public Adicional verAdicional(@PathVariable("id") Long id) {
        return adicionalService.findById(id).orElse(null);
    }

    // Formulario para agregar un adicional
    //http://localhost:8090/adicionales/agregar
    @GetMapping("/agregar")
    public String mostrarFormularioAgregarAdicional(Model model) {
        model.addAttribute("adicional", new Adicional());
        return "AgregarAdicional";
    }

    // Guardar un nuevo adicional
    //http://localhost:8090/adicionales/add
    @PostMapping("/add")
    public void agregarAdicional(@RequestBody Adicional adicional) {
        adicionalService.add(adicional);
    }

    // Eliminar un adicional por ID
    //http://localhost:8090/adicionales/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarAdicional(@PathVariable Long id) {
        try {
            Optional<Adicional> adicionalOpt = adicionalService.findById(id);
            if (adicionalOpt.isPresent()) {
                Adicional adicional = adicionalOpt.get();
    
                // Eliminar las relaciones en la tabla intermedia
                adicional.getProductos().forEach(producto -> producto.getAdicionales().remove(adicional));
    
                // Guardar los cambios en los productos
                adicionalService.updateProductos(adicional.getProductos());
    
                // Eliminar el adicional
                adicionalService.deleteById(id);
                return ResponseEntity.ok("Adicional eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El adicional con ID " + id + " no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el adicional: " + e.getMessage());
        }
    }

    // Formulario de edición de un adicional
    //http://localhost:8090/adicionales/update/id
    @PutMapping("/update/{id}")
    public void modificarAdicional(@RequestBody Adicional adicional, @PathVariable("id") Long id) {
        adicional.setId(id);
        adicionalService.update(adicional);
    }

    // Guardar cambios de un adicional
    //http://localhost:8090/adicionales/update/id
    @PostMapping("/update/{id}")
    public String modificarAdicional(
        @PathVariable("id") Long id,
        @ModelAttribute Adicional adicional,
        Model model
    ) {
        Optional<Adicional> adicionalExistente = adicionalService.findById(id);
        if (adicionalExistente.isPresent()) {
            Adicional adicionalActualizado = adicionalExistente.get();
            adicionalActualizado.setNombre(adicional.getNombre());
            adicionalActualizado.setPrecio(adicional.getPrecio());
            adicionalActualizado.setDescripcion(adicional.getDescripcion());

            // Guardar los cambios
            adicionalService.update(adicionalActualizado);

            return "redirect:/adicionales/view/" + id;
        } else {
            model.addAttribute("error", "El adicional no existe.");
            model.addAttribute("adicional", adicional);
            return "EditarAdicional";
        }
    }
}