package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Producto;
import com.example.demo.servicio.ProductoService;
import com.example.demo.repositorio.ProductoRepository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired    
    private ProductoRepository productoRepository;
   
}