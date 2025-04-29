package com.example.demo.servicio;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoComprasService {

    @Autowired
    private CarritoComprasRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Buscar carrito existente por clienteId
    public CarritoCompras buscarCarritoPorCliente(Integer clienteId) {
        return carritoRepository.findByClienteModelId(clienteId);
    }

    // Crear carrito nuevo para un cliente
    public CarritoCompras crearCarrito(Integer clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId.longValue());
        if (clienteOpt.isPresent()) {
            CarritoCompras carrito = new CarritoCompras(clienteOpt.get());
            carrito.setPrecioTotal(0);
            return carritoRepository.save(carrito);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado para id: " + clienteId);
        }
    }
}
