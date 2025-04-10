package com.example.demo.controlador;
import org.springframework.ui.Model;
import com.example.demo.entidades.Adicional;
import com.example.demo.servicio.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adicionales")
@CrossOrigin(origins = "http://localhost:4200") // Asegúrate de permitir solicitudes desde tu aplicación Angular
public class AdicionalController {

    @Autowired
    private AdicionalService adicionalService;

    // Obtener todos los adicionales
    @GetMapping("/all")
    public List<Adicional> listarAdicionales() {
        return adicionalService.findAll(); 
    }

    // Obtener un adicional por ID
    @GetMapping("/view/{id}")
    public Adicional getAdicionalById(@PathVariable("id") Long id) {
        return adicionalService.findById(id).orElse(null); // Devuelve el adicional por ID
    }

    // Agregar un nuevo adicional
    @PostMapping("/add")
    public void addAdicional(@RequestBody Adicional adicional) {
        adicionalService.add(adicional); // Agrega el adicional
    }

    // Eliminar un adicional por ID
    @DeleteMapping("/delete/{id}")
    public void deleteAdicional(@PathVariable("id") Long id) {
        adicionalService.deleteById(id); // Elimina el adicional por ID
    }

    // Actualizar un adicional
    @PutMapping("/update/{id}")
    public void updateAdicional(@RequestBody Adicional adicional, @PathVariable("id") Long id) {
        adicional.setId(id);  // Establece el ID para asegurarse de que se actualice el correcto
        adicionalService.update(adicional); // Actualiza el adicional
    }
}