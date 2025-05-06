package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.demo.entidades.*;
import com.example.demo.repositorio.*;
import com.example.demo.servicio.PedidoService;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PedidoServiceMockTest {

  @Mock
  private PedidoRepository pedidoRepository;

  @Mock
  private ClienteRepository clienteRepository;

  @Mock
  private CarritoComprasRepository carritoRepository;

  @Mock
  private SeleccionarProductosRepository seleccionarProductosRepository;

  @InjectMocks
  private PedidoService pedidoService;

  public PedidoServiceMockTest() {
    MockitoAnnotations.openMocks(this); // Inicializa los @Mock
  }

  @Test
  public void testConfirmarPedidoClienteConProductos() {
    Cliente cliente = new Cliente(
      "Ana",
      "Test",
      "ana@test.com",
      "clave123",
      "1234567890",
      "Calle 123"
    );
    CarritoCompras carrito = new CarritoCompras(cliente);
    carrito.setProductosSeleccionados(List.of(new SeleccionarProductos()));

    when(carritoRepository.findByClienteId(1L)).thenReturn(carrito);
    when(pedidoRepository.save(any(Pedido.class)))
      .thenAnswer(i -> i.getArgument(0));

    Pedido pedido = pedidoService.confirmarPedido(1L);

    assertThat(pedido).isNotNull();
    assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.RECIBIDO);
    verify(pedidoRepository, times(1)).save(any());
  }

  @Test
  public void testBuscarPedidosPorCarritoClienteId() {
    Pedido pedido = new Pedido();
    pedido.setEstado(EstadoPedido.RECIBIDO);
    pedido.setTotal(40000.0);

    when(pedidoRepository.findByCarritoClienteId(1L))
      .thenReturn(List.of(pedido));

    List<Pedido> pedidos = pedidoRepository.findByCarritoClienteId(1L);

    assertThat(pedidos).isNotEmpty();
    assertThat(pedidos.get(0).getEstado()).isEqualTo(EstadoPedido.RECIBIDO);
    verify(pedidoRepository, times(1)).findByCarritoClienteId(1L);
  }

  @Test
  public void testBuscarPedidosPorEstado() {
    Pedido pedido = new Pedido();
    pedido.setEstado(EstadoPedido.COCINANDO);
    pedido.setTotal(20000.0);

    when(pedidoRepository.findByEstado("COCINANDO"))
      .thenReturn(List.of(pedido));

    List<Pedido> pedidos = pedidoRepository.findByEstado("COCINANDO");

    assertThat(pedidos).hasSize(1);
    assertThat(pedidos.get(0).getEstado()).isEqualTo(EstadoPedido.COCINANDO);
    verify(pedidoRepository, times(1)).findByEstado("COCINANDO");
  }

  @Test
  public void testBuscarPedidosPorOperadorId() {
    Pedido pedido = new Pedido();
    pedido.setEstado(EstadoPedido.RECIBIDO);
    pedido.setTotal(35000.0);

    when(pedidoRepository.findByOperadorId(10)).thenReturn(List.of(pedido));

    List<Pedido> pedidos = pedidoRepository.findByOperadorId(10);

    assertThat(pedidos).isNotEmpty();
    assertThat(pedidos.get(0).getEstado()).isEqualTo(EstadoPedido.RECIBIDO);
    verify(pedidoRepository).findByOperadorId(10);
  }
}
