package com.example.demo.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Cliente;
import com.example.demo.servicio.ClienteService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
   
    @Autowired
    private ClienteService service;
   
}