package com.example.demo.servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.ClienteRepository;
@Service
public class CarritoComprasService {

    
    @Autowired
    private CarritoComprasRepository CarritoComprasRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    public CarritoCompras crearCarrito(Long clienteId) {
    // Si clienteId es proporcionado, asociamos el carrito al cliente
    Cliente cliente = clienteRepository.findById(clienteId)
    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    CarritoCompras carrito = new CarritoCompras(cliente);
    CarritoComprasRepository.save(carrito);
    System.out.println( "carrito añadido perfectamente " + carrito);
    return carrito;
    }
}
