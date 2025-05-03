package com.example.demo.entidades;

import jakarta.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
        name = "producto_seleccionado_adicionales",
        joinColumns = @JoinColumn(name = "producto_seleccionado_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionales;

    public SeleccionarProductos() {}

    public SeleccionarProductos(Integer id, int cantidad, CarritoCompras carrito, Producto producto, List<Adicional> adicionales) {
        this.id = id;
        this.cantidad = cantidad;
        this.carrito = carrito;
        this.producto = producto;
        this.adicionales = adicionales;
    }

    // Getters y Setters

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

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }
}
