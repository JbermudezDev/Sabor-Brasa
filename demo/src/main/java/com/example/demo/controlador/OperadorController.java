package com.example.demo.controlador;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Operador;
import com.example.demo.servicio.OperadorService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operadores")
public class OperadorController {
    private final OperadorService operadorService;

    public OperadorController(OperadorService operadorService) {
        this.operadorService = operadorService;
    }

    @GetMapping
    public List<Operador> listarTodos() {
        return operadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Operador> buscarPorId(@PathVariable Long id) {
        return operadorService.buscarPorId(id);
    }

    @PostMapping
    public Operador crear(@RequestBody Operador operador) {
        return operadorService.guardar(operador);
    }

    @PutMapping("/{id}")
    public Operador actualizar(@PathVariable Long id, @RequestBody Operador operador) {
        operador.setId(id);
        return operadorService.guardar(operador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        operadorService.eliminarPorId(id);
    }
}