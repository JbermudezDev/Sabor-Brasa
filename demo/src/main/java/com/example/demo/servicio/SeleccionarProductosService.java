package com.example.demo.servicio;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.Producto;
import com.example.demo.entidades.SeleccionarProductos;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.ProductoRepository;
import com.example.demo.repositorio.SeleccionarProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeleccionarProductosService {

    @Autowired
    private SeleccionarProductosRepository seleccionarProductosRepository;

    @Autowired
    private CarritoComprasRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public SeleccionarProductos agregarProductoACarrito(Long clienteId, Long productoId, int cantidad) {
        CarritoCompras carrito = carritoRepository.findByClienteId(clienteId);
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (carrito == null || producto == null) return null;

        SeleccionarProductos seleccion = new SeleccionarProductos();
        seleccion.setCarrito(carrito);
        seleccion.setProducto(producto);
        seleccion.setCantidad(cantidad);

        return seleccionarProductosRepository.save(seleccion);
    }

    public List<SeleccionarProductos> listarPorCarrito(Integer carritoId) {
        return seleccionarProductosRepository.findByCarritoId(carritoId);
    }

    public void eliminarProducto(Integer seleccionId) {
        seleccionarProductosRepository.deleteById(seleccionId);
    }
}
