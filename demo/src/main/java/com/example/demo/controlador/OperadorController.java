package com.example.demo.controlador;

import com.example.demo.entidades.Operador;
import com.example.demo.servicio.OperadorService;
import com.example.demo.DTO.OperadorDTO;
import com.example.demo.DTO.OperadorMapper;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/operadores")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class OperadorController {

    @Autowired
    private OperadorService operadorService;


    //http://localhost:8090/operadores/all
    // Obtener todos los operadores
    @GetMapping("/all")
    public List<Operador> obtenerTodosLosOperadores() {
        return operadorService.searchAll();
    }

    //http://localhost:8090/operadores/find/id
    // Obtener un operador por ID
    @GetMapping("/find/{id}")
    public Operador obtenerOperadorPorId(@PathVariable("id") Integer id) {
        return operadorService.findById(id).orElse(null);
    }

    // http://localhost:8090/operadores/add
    // Agregar un nuevo operador
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> agregarOperador(@RequestBody OperadorDTO operadorDTO) {
        try {
            Operador operador = OperadorMapper.INSTANCE.convert(operadorDTO);
            operadorService.add(operador);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Operador añadido correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al añadir el operador: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    //http://localhost:8090/operadores/update/id
    // Actualizar un operador existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Operador> actualizarOperador(@PathVariable Integer id, @RequestBody Operador operadorActualizado) {
    // Buscar el operador existente por ID
    Optional<Operador> operadorExistente = operadorService.findById(id);

    if (operadorExistente.isPresent()) {
        Operador operador = operadorExistente.get();

    // Actualizar los campos del operador existente con los datos del operador actualizado
     operador.setNombre(operadorActualizado.getNombre());
    operador.setUsuario(operadorActualizado.getUsuario());
    operador.setContrasena(operadorActualizado.getContrasena());

    // Guardar los cambios en la base de datos
    operadorService.update(operador);

     return ResponseEntity.ok(operador);
    }

    // Si el operador no existe, devolver un 404
    return ResponseEntity.notFound().build();
}

    //http://localhost:8090/operadores/delete/id
    // Eliminar un operador por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarOperador(@PathVariable Integer id) {
    try {
        // Verificar si el operador existe
        if (!operadorService.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El operador con ID " + id + " no existe");
        }

        // Eliminar el operador
        operadorService.deleteById(id);
        return ResponseEntity.ok("Operador eliminado correctamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el operador: " + e.getMessage());
    }
}
@GetMapping("details")
public ResponseEntity<OperadorDTO> obtenerDetallesOperador() {
    Operador operador = operadorService.findByEmail(
        securityContextHolder.getContext().getAuthentication().getName()
    );
    OperadorDTO operadorDTO = OperadorMapper.INSTANCE.convert(operador);
    if (operador == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operadorDTO);
    } else {
        return new ResponseEntity<>(operadorDTO, HttpStatus.OK);
    }
}