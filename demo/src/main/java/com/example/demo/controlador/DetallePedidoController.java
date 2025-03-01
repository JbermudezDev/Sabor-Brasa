package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.DetallePedido;
import com.example.demo.servicio.DetallePedidoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle-pedido")
public class DetallePedidoController {
    @Autowired
    private DetallePedidoService detallePedidoService;
   
}