package com.example.demo.controlador;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.servicio.DomiciliarioService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domiciliarios")
public class DomiciliarioController {
    private final DomiciliarioService domiciliarioService;

    public DomiciliarioController(DomiciliarioService domiciliarioService) {
        this.domiciliarioService = domiciliarioService;
    }

    @GetMapping
    public List<Domiciliario> listarTodos() {
        return domiciliarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Domiciliario> buscarPorId(@PathVariable Long id) {
        return domiciliarioService.buscarPorId(id);
    }

    @PostMapping
    public Domiciliario crear(@RequestBody Domiciliario domiciliario) {
        return domiciliarioService.guardar(domiciliario);
    }

    @PutMapping("/{id}")
    public Domiciliario actualizar(@PathVariable Long id, @RequestBody Domiciliario domiciliario) {
        domiciliario.setId(id);
        return domiciliarioService.guardar(domiciliario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        domiciliarioService.eliminarPorId(id);
    }
}