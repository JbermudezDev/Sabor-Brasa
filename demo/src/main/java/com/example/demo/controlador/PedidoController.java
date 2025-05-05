package com.example.demo.controlador;

import com.example.demo.DTO.PedidoRequestDTO;
import com.example.demo.entidades.*;
import com.example.demo.servicio.PedidoService;
import com.example.demo.servicio.OperadorService;
import com.example.demo.servicio.DomiciliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private OperadorService operadorService;

    @Autowired
    private DomiciliarioService domiciliarioService;

    @PostMapping("/confirmar/{clienteId}")
    public ResponseEntity<?> confirmarPedido(@PathVariable Long clienteId) {
        Pedido pedido = pedidoService.confirmarPedido(clienteId);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Carrito no encontrado o vacío");
        }
        return ResponseEntity.ok(pedido);
    }
    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPedido(@RequestBody PedidoRequestDTO request) {
        Pedido pedido = pedidoService.confirmarPedido(request);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Carrito vacío o error en los datos");
        }
        return ResponseEntity.ok(pedido);
    }

   


    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PutMapping("/actualizarEstado/{pedidoId}")
    public ResponseEntity<?> actualizarEstado(
        @PathVariable Integer pedidoId,
        @RequestParam EstadoPedido estado,
        @RequestParam Integer operadorId
    ) {
        Operador operador = operadorService.findById(operadorId).orElse(null);
        if (operador == null) return ResponseEntity.badRequest().body("Operador inválido");

        Pedido actualizado = pedidoService.actualizarEstado(pedidoId, estado, operador);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/asignarDomiciliario/{pedidoId}")
    public ResponseEntity<?> asignarDomiciliario(
        @PathVariable Integer pedidoId,
        @RequestParam Integer domiciliarioId
    ) {
        Domiciliario domiciliario = domiciliarioService.buscarPorId(domiciliarioId).orElse(null);
        if (domiciliario == null || !domiciliario.isDisponibilidad()) {
            return ResponseEntity.badRequest().body("Domiciliario no disponible o no válido");
        }

        Pedido actualizado = pedidoService.asignarDomiciliario(pedidoId, domiciliario);
        return ResponseEntity.ok(actualizado);
    }
}
