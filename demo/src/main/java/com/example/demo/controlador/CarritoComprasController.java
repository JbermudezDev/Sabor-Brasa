package com.example.demo.controlador;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.servicio.CarritoComprasService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class CarritoComprasController {

  @Autowired
  private CarritoComprasService carritoComprasService;

  @PostMapping("/guardar")
  public ResponseEntity<?> guardarCarrito(@RequestBody CarritoCompras carrito) {
    try {
      CarritoCompras carritoGuardado = carritoComprasService.guardar(carrito);
      return ResponseEntity.ok(carritoGuardado);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("Error al guardar el carrito: " + e.getMessage());
    }
  }
}
