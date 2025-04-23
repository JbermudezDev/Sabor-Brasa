package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "seleccionar_productos")
public class SeleccionarProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private CarritoCompras carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public SeleccionarProductos() {
    }

    public SeleccionarProductos(Integer id, int cantidad, CarritoCompras carrito, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.carrito = carrito;
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CarritoCompras getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoCompras carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
