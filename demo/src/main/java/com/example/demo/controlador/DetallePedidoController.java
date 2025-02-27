package com.example.demo.controlador;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.DetallePedido;
import com.example.demo.servicio.DetallePedidoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle-pedido")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedido> listarTodos() {
        return detallePedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<DetallePedido> buscarPorId(@PathVariable Long id) {
        return detallePedidoService.buscarPorId(id);
    }

    @PostMapping
    public DetallePedido crear(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.guardar(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizar(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        detallePedido.setId(id);
        return detallePedidoService.guardar(detallePedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detallePedidoService.eliminarPorId(id);
    }
}