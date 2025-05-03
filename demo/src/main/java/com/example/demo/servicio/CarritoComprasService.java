package com.example.demo.servicio;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoComprasService {

    @Autowired
    private CarritoComprasRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public CarritoCompras buscarCarritoPorCliente(Long clienteId) {
        return carritoRepository.findByClienteModelId(clienteId);
    }

    public CarritoCompras crearCarrito(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) return null;

        CarritoCompras carrito = new CarritoCompras(cliente);
        return carritoRepository.save(carrito);
    }

    public void actualizar(CarritoCompras carrito) {
        carritoRepository.save(carrito);
    }
}
