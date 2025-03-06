package com.example.demo.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.servicio.DomiciliarioService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domiciliarios")
public class DomiciliarioController {
   @Autowired
   private DomiciliarioService domiciliarioService;

   
}