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

    //http://localhost:8090/domiciliarios/all
    @GetMapping("/all")
    public List<Domiciliario> obtenerTodos() {
        return domiciliarioService.obtenerTodos();
    }

    //http://localhost:8090/domiciliarios/disponibles
    @GetMapping("/disponibles")
    public List<Domiciliario> obtenerDisponibles() {
        return domiciliarioService.obtenerDisponibles();
    }

    //http://localhost:8090/domiciliarios/find/{id}
    @GetMapping("/find/{id}")
    public ResponseEntity<Domiciliario> obtenerPorId(@PathVariable Integer id) {
        return domiciliarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //http://localhost:8090/domiciliarios/add
    @PostMapping("/add")
    public Domiciliario agregar(@RequestBody Domiciliario d) {
        return domiciliarioService.guardar(d);
    }

    //http://localhost:8090/domiciliarios/update/{id}
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

    //http://localhost:8090/domiciliarios/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        domiciliarioService.eliminar(id);
        return ResponseEntity.ok("Domiciliario eliminado correctamente");
    }
}
