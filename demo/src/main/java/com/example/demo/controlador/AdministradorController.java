package com.example.demo.controlador;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Administrador;
import com.example.demo.servicio.AdministradorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> buscarPorId(@PathVariable Long id) {
        return administradorService.buscarPorId(id);
    }

    @PostMapping
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorService.guardar(administrador);
    }

    @PutMapping("/{id}")
    public Administrador actualizar(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorService.guardar(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        administradorService.eliminarPorId(id);
    }
}