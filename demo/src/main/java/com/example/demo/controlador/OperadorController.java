package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Operador;
import com.example.demo.servicio.OperadorService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operadores")
public class OperadorController {
    @Autowired
    private OperadorService operadorService;

    
}