package com.example.demo.service;

import com.example.demo.entidades.*;
import com.example.demo.repositorio.*;
import com.example.demo.servicio.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PedidoServiceMockTest {

    @Mock private PedidoRepository pedidoRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private CarritoComprasRepository carritoRepository;
    @Mock private SeleccionarProductosRepository seleccionarProductosRepository;

    @InjectMocks private PedidoService pedidoService;

    public PedidoServiceMockTest() {
        MockitoAnnotations.openMocks(this);  // Inicializa los @Mock
    }

    @Test
    public void testConfirmarPedidoClienteConProductos() {
        Cliente cliente = new Cliente("Ana", "Test", "ana@test.com", "clave123", "1234567890", "Calle 123");
        CarritoCompras carrito = new CarritoCompras(cliente);
        carrito.setProductosSeleccionados(List.of(new SeleccionarProductos()));

        when(carritoRepository.findByClienteId(1L)).thenReturn(carrito);
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        Pedido pedido = pedidoService.confirmarPedido(1L);

        assertThat(pedido).isNotNull();
        assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.RECIBIDO);
        verify(pedidoRepository, times(1)).save(any());
    }
}
