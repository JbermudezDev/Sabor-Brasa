package com.example.demo.service;

import com.example.demo.DTO.PedidoRequestDTO;
import com.example.demo.entidades.*;
import com.example.demo.repositorio.*;
import com.example.demo.servicio.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PedidoServiceTestNaive {

    @Autowired private PedidoService pedidoService;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private AdicionalRepository adicionalRepository;
    @Autowired private CarritoComprasRepository carritoRepository;
    @Autowired private SeleccionarProductosRepository seleccionarProductosRepository;
    @Autowired private OperadorRepository operadorRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private DomiciliarioRepository domiciliarioRepository;

    @Test
    public void testConfirmarPedidoDesdeDTO() {
        Cliente cliente = clienteRepository.save(new Cliente("Laura", "López", "laura@test.com", "clave1234", "3001112233", "Calle 1"));
        Producto producto = productoRepository.save(new Producto("Pizza", 25000f, "Mediana", "imagen.jpg"));
        Adicional adicional = adicionalRepository.save(new Adicional("Queso extra", 3000f, "Extra de queso"));

        PedidoRequestDTO.ItemDTO item = new PedidoRequestDTO.ItemDTO();
        item.setProductoId(producto.getId());
        item.setAdicionales(List.of(adicional.getId()));

        PedidoRequestDTO request = new PedidoRequestDTO();
        request.setClienteId(cliente.getId());
        request.setItems(List.of(item));

        Pedido pedido = pedidoService.confirmarPedido(request);

        assertThat(pedido).isNotNull();
        assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.RECIBIDO);
        assertThat(pedido.getCliente().getId()).isEqualTo(cliente.getId());
        assertThat(pedido.getTotal()).isEqualTo(28000.0);
    }

   

    @Test
    public void testListarPedidosPorCliente() {
        Cliente cliente = clienteRepository.save(new Cliente("Marta", "Zapata", "marta@test.com", "clave1234", "3022233445", "Carrera 8"));
        CarritoCompras carrito = carritoRepository.save(new CarritoCompras(cliente));
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setCarrito(carrito);
        pedido.setEstado(EstadoPedido.RECIBIDO);
        pedido.setFechaCreacion(new Date());
        pedido.setTotal(50000.0);
        pedidoRepository.save(pedido);

        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(cliente.getId());

        assertThat(pedidos).isNotEmpty();
        assertThat(pedidos.get(0).getCliente().getId()).isEqualTo(cliente.getId());
    }

    @Test
    public void testActualizarEstadoDePedido() {
        Cliente cliente = clienteRepository.save(new Cliente("Julio", "César", "julio@test.com", "clave1234", "3009998877", "Calle 20"));
        CarritoCompras carrito = carritoRepository.save(new CarritoCompras(cliente));
        Operador operador = operadorRepository.save(new Operador(null, "Mario", "mario_op", "claveop123"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setCarrito(carrito);
        pedido.setEstado(EstadoPedido.RECIBIDO);
        pedido.setFechaCreacion(new Date());
        pedido.setTotal(15000.0);
        pedido = pedidoRepository.save(pedido);

        Pedido actualizado = pedidoService.actualizarEstado(pedido.getId(), EstadoPedido.COCINANDO, operador);

        assertThat(actualizado.getEstado()).isEqualTo(EstadoPedido.COCINANDO);
        assertThat(actualizado.getOperador().getUsuario()).isEqualTo("mario_op");
    }

    @Test
    public void testAsignarDomiciliario() {
        Cliente cliente = clienteRepository.save(new Cliente("Paula", "Santos", "paula@test.com", "clavePaula", "3004445566", "Calle 50"));
        CarritoCompras carrito = carritoRepository.save(new CarritoCompras(cliente));
        Domiciliario domiciliario = domiciliarioRepository.save(new Domiciliario(null, "Luis", "3123456789", "123456789", true));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setCarrito(carrito);
        pedido.setEstado(EstadoPedido.RECIBIDO);
        pedido.setFechaCreacion(new Date());
        pedido.setTotal(19000.0);
        pedido = pedidoRepository.save(pedido);

        Pedido actualizado = pedidoService.asignarDomiciliario(pedido.getId(), domiciliario);

        assertThat(actualizado).isNotNull();
        assertThat(actualizado.getDomiciliario().getNombre()).isEqualTo("Luis");
        
    }
}
