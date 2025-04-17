package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.servicio.AdicionalService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/all")
    public List<Adicional> listarAdicionales() {
        return adicionalService.findAll();
    }

    // Ver detalles de un adicional por ID
    @GetMapping("/find/{id}")
    public Adicional verAdicional(@PathVariable("id") Long id) {
        return adicionalService.findById(id).orElse(null);
    }

    // Formulario para agregar un adicional
    @GetMapping("/agregar")
    public String mostrarFormularioAgregarAdicional(Model model) {
        model.addAttribute("adicional", new Adicional());
        return "AgregarAdicional";
    }

    // Guardar un nuevo adicional
    @PostMapping("/add")
    public void agregarAdicional(@RequestBody Adicional adicional) {
        adicionalService.add(adicional);
    }

    // Eliminar un adicional por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarAdicional(@PathVariable Long id) {
        adicionalService.deleteById(id);
        return ResponseEntity.noContent().build(); // Devuelve un código 204 (No Content)
    }

    // Formulario de edición de un adicional
    @PutMapping("/update/{id}")
    public void modificarAdicional(@RequestBody Adicional adicional, @PathVariable("id") Long id) {
        adicional.setId(id);
        adicionalService.update(adicional);
    }

    // Guardar cambios de un adicional
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