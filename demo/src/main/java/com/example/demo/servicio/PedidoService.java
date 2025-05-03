package com.example.demo.servicio;

import com.example.demo.entidades.*;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoComprasRepository carritoRepository;

    public Pedido confirmarPedido(Long clienteId) {
        CarritoCompras carrito = carritoRepository.findByClienteModelId(clienteId);
        if (carrito == null || carrito.getProductosSeleccionados().isEmpty()) return null;

        Pedido pedido = new Pedido();
        pedido.setEstado(EstadoPedido.RECIBIDO);
        pedido.setFechaCreacion(new Date());
        pedido.setCarrito(carrito);
        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByCarritoClienteModelId(clienteId);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido actualizarEstado(Integer pedidoId, EstadoPedido nuevoEstado, Operador operador) {
        Optional<Pedido> optional = pedidoRepository.findById(pedidoId);
        if (optional.isEmpty()) return null;

        Pedido pedido = optional.get();
        pedido.setEstado(nuevoEstado);
        pedido.setOperador(operador);
        if (nuevoEstado == EstadoPedido.ENTREGADO) {
            pedido.setFechaEntrega(new Date());
            if (pedido.getDomiciliario() != null) {
                pedido.getDomiciliario().setDisponibilidad(true);
            }
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido asignarDomiciliario(Integer pedidoId, Domiciliario domiciliario) {
        Optional<Pedido> optional = pedidoRepository.findById(pedidoId);
        if (optional.isEmpty()) return null;

        Pedido pedido = optional.get();
        domiciliario.setDisponibilidad(false);
        pedido.setDomiciliario(domiciliario);
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }
}