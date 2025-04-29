package com.example.demo.controlador;

import com.example.demo.dto.PedidoRequest;
import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.Pedido;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.PedidoRepository;
import com.example.demo.repositorio.SeleccionarProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoComprasRepository carritoRepository;

    @Autowired
    private SeleccionarProductosRepository seleccionarProductosRepository;

    @PostMapping("/confirmar/{clienteId}")
    public ResponseEntity<String> confirmarPedido(
            @PathVariable Integer clienteId,
            @RequestBody PedidoRequest pedidoRequest
    ) {
        // Buscar el carrito activo del cliente
        CarritoCompras carrito = carritoRepository.findByClienteModelId(clienteId);

        if (carrito == null) {
            return ResponseEntity.badRequest().body("Carrito no encontrado para el cliente");
        }

        if (pedidoRequest.getProductos() == null || pedidoRequest.getProductos().isEmpty()) {
            return ResponseEntity.badRequest().body("No se enviaron productos para confirmar");
        }

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setEstado("Confirmado");
        pedido.setFechaCreacion(new Date());
        pedido.setFechaEntrega(new Date(System.currentTimeMillis() + (2 * 60 * 60 * 1000))); // 2 horas después
        pedido.setCarrito(carrito);

        pedidoRepository.save(pedido);

        // Opcional: limpiar los productos seleccionados del carrito
        carrito.getProductosSeleccionados().clear();
        carritoRepository.save(carrito);

        return ResponseEntity.ok("Pedido confirmado exitosamente");
    }

    @GetMapping("/listar")
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPedidosPorCliente(@PathVariable Integer clienteId) {
        List<Pedido> pedidos = pedidoRepository.findByCarritoClienteModelId(clienteId);
        return ResponseEntity.ok(pedidos);
    }
}
