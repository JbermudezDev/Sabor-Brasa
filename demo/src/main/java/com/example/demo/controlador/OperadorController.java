package com.example.demo.controlador;

import com.example.demo.entidades.Operador;
import com.example.demo.servicio.OperadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operadores")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    // Obtener todos los operadores
    @GetMapping("/all")
    public List<Operador> obtenerTodosLosOperadores() {
        return operadorService.searchAll();
    }

    // Obtener un operador por ID
    @GetMapping("/find/{id}")
    public Operador obtenerOperadorPorId(@PathVariable("id") Integer id) {
        return operadorService.findById(id).orElse(null);
    }

    // Agregar un nuevo operador
    @PostMapping("/add")
    public void agregarOperador(@RequestBody Operador operador) {
        operadorService.add(operador);
    }

    // Actualizar un operador existente
    @PutMapping("/update/{id}")
    public void actualizarOperador(@PathVariable("id") Integer id, @RequestBody Operador operador) {
        operador.setId(id); // Asegurarse de que el ID sea el correcto
        operadorService.update(operador);
    }

    // Eliminar un operador por ID
    @DeleteMapping("/delete/{id}")
    public void eliminarOperador(@PathVariable("id") Integer id) {
        operadorService.deleteById(id);
    }
}