package com.example.demo.controlador;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.servicio.CarritoComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoComprasController {

    @Autowired
    private CarritoComprasService carritoService;

    //http://localhost:8090/carrito/clienteid
    @GetMapping("/{clienteId}")
    public CarritoCompras obtenerCarritoPorCliente(@PathVariable Long clienteId) {
        return carritoService.buscarCarritoPorCliente(clienteId);
    }
}
