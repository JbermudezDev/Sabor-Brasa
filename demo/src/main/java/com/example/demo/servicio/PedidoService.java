package com.example.demo.servicio;

import com.example.demo.dto.PedidoRequestDTO;
import com.example.demo.entidades.*;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.PedidoRepository;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.ProductoRepository;
import com.example.demo.repositorio.AdicionalRepository;
import com.example.demo.repositorio.SeleccionarProductosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SeleccionarProductosRepository seleccionarProductoRepository;

    @Autowired
    private CarritoComprasRepository carritoRepository;


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Autowired
    private DomiciliarioService domiciliarioService;
    

   
    

    public Pedido confirmarPedido(Long clienteId) {
        CarritoCompras carrito = carritoRepository.findByClienteId(clienteId);
        if (carrito == null || carrito.getProductosSeleccionados().isEmpty()) return null;

        Pedido pedido = new Pedido();
        pedido.setEstado(EstadoPedido.RECIBIDO);
        pedido.setFechaCreacion(new Date());
        pedido.setCarrito(carrito);
        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByCarritoClienteId(clienteId);
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

    public Pedido confirmarPedido(PedidoRequestDTO request) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(request.getClienteId());
    if (clienteOpt.isEmpty()) return null;

    Cliente cliente = clienteOpt.get();

    CarritoCompras carrito = new CarritoCompras();
    carrito.setCliente(cliente);
    carrito = carritoRepository.save(carrito);

    List<SeleccionarProductos> seleccionados = new ArrayList<>();
    double total = 0.0;

    for (PedidoRequestDTO.ItemDTO item : request.getItems()) {
        Optional<Producto> productoOpt = productoRepository.findById(item.getProductoId());
        if (productoOpt.isEmpty()) continue;

        Producto producto = productoOpt.get();

        SeleccionarProductos sel = new SeleccionarProductos();
        sel.setProducto(producto);
        sel.setCarrito(carrito);

        List<Adicional> adicionales = new ArrayList<>();
        if (item.getAdicionales() != null) {
            for (Long adicionalId : item.getAdicionales()) {
                adicionalRepository.findById(adicionalId).ifPresent(adicional -> {
                    adicionales.add(adicional);
                });
            }
        }

        sel.setAdicionales(adicionales);
        sel = seleccionarProductoRepository.save(sel);

        seleccionados.add(sel);
        total += producto.getPrecio();
        total += adicionales.stream().mapToDouble(Adicional::getPrecio).sum();
    }

    Pedido pedido = new Pedido();
    pedido.setCliente(cliente);
    pedido.setFechaCreacion(java.util.Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
    pedido.setEstado(EstadoPedido.RECIBIDO);
    pedido.setCarrito(carrito);
    pedido.setTotal(total);

    return pedidoRepository.save(pedido);
}

public Pedido actualizarEstado(Integer pedidoId, EstadoPedido nuevoEstado, Operador operador, Integer domiciliarioId) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

    pedido.setEstado(nuevoEstado);
    pedido.setOperador(operador);

    if (domiciliarioId != null) {
        Domiciliario domiciliario = domiciliarioService.buscarPorId(domiciliarioId)
            .orElseThrow(() -> new IllegalArgumentException("Domiciliario no encontrado"));
        pedido.setDomiciliario(domiciliario);
    }

    return pedidoRepository.save(pedido);
}

}