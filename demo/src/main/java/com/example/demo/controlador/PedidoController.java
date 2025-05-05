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

     //http://localhost:8090/pedidos/confirmar/id
    @PostMapping("/confirmar/{clienteId}")
    public ResponseEntity<?> confirmarPedido(@PathVariable Long clienteId) {
        Pedido pedido = pedidoService.confirmarPedido(clienteId);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Carrito no encontrado o vacío");
        }
        return ResponseEntity.ok(pedido);
    }

    //http://localhost:8090/pedidos/confirmar
    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPedido(@RequestBody PedidoRequestDTO request) {
        Pedido pedido = pedidoService.confirmarPedido(request);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Carrito vacío o error en los datos");
        }
        return ResponseEntity.ok(pedido);
    }

   

    //http://localhost:8090/pedidos/cliente/id
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    //http://localhost:8090/pedidos/all
    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    //http://localhost:8090/pedidos/actualizarPedido/id
    @PutMapping("/actualizarPedido/{pedidoId}")
    public ResponseEntity<?> actualizarEstado(
    @PathVariable Integer pedidoId,
    @RequestParam String estado, // ahora como String
    @RequestParam Integer operadorId,
    @RequestParam(required = false) Integer domiciliarioId
) {
    Operador operador = operadorService.findById(operadorId).orElse(null);
    if (operador == null) return ResponseEntity.badRequest().body("Operador inválido");

    EstadoPedido estadoEnum;
    try {
        estadoEnum = EstadoPedido.valueOf(estado.toUpperCase());
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Estado inválido");
    }

    Pedido actualizado = pedidoService.actualizarEstado(pedidoId, estadoEnum, operador, domiciliarioId);
    return ResponseEntity.ok(actualizado);
}


    //http://localhost:8090/pedidos/asignarDomiciliario/id
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
