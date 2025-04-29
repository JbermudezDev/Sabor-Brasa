package com.example.demo.controlador;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.servicio.CarritoComprasService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class CarritoComprasController {

    
}