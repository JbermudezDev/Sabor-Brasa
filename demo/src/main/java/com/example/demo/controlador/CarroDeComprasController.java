package com.example.demo.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entidades.CarroDeCompras;
import com.example.demo.servicio.CarroDeComprasService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro-de-compras")
public class CarroDeComprasController {

    @Autowired
    private CarroDeComprasService carroDeComprasService;
    

    
}