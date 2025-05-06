package com.example.demo.DTO;

import java.util.List;

public class PedidoRequest {

  private List<ProductoSeleccionado> productos;

  public PedidoRequest() {} // Constructor vacío obligatorio

  public List<ProductoSeleccionado> getProductos() {
    return productos;
  }

  public void setProductos(List<ProductoSeleccionado> productos) {
    this.productos = productos;
  }

  public static class ProductoSeleccionado {

    private Integer productoId;
    private List<Integer> adicionales;

    public ProductoSeleccionado() {}

    public Integer getProductoId() {
      return productoId;
    }

    public void setProductoId(Integer productoId) {
      this.productoId = productoId;
    }

    public List<Integer> getAdicionales() {
      return adicionales;
    }

    public void setAdicionales(List<Integer> adicionales) {
      this.adicionales = adicionales;
    }
  }
}
