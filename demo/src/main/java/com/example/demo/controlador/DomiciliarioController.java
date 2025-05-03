package com.example.demo.controlador;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.servicio.DomiciliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domiciliarios")
@CrossOrigin(origins = "http://localhost:4200")
public class DomiciliarioController {

    @Autowired
    private DomiciliarioService domiciliarioService;

    @GetMapping("/all")
    public List<Domiciliario> obtenerTodos() {
        return domiciliarioService.obtenerTodos();
    }

    @GetMapping("/disponibles")
    public List<Domiciliario> obtenerDisponibles() {
        return domiciliarioService.obtenerDisponibles();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Domiciliario> obtenerPorId(@PathVariable Integer id) {
        return domiciliarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Domiciliario agregar(@RequestBody Domiciliario d) {
        return domiciliarioService.guardar(d);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Domiciliario> actualizar(@PathVariable Integer id, @RequestBody Domiciliario actualizado) {
        return domiciliarioService.buscarPorId(id).map(d -> {
            d.setNombre(actualizado.getNombre());
            d.setCelular(actualizado.getCelular());
            d.setCedula(actualizado.getCedula());
            d.setDisponibilidad(actualizado.isDisponibilidad());
            return ResponseEntity.ok(domiciliarioService.guardar(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        domiciliarioService.eliminar(id);
        return ResponseEntity.ok("Domiciliario eliminado correctamente");
    }
}
