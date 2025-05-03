package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carro_de_compras")
public class CarritoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private float precioTotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteModel;

    @JsonIgnore
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeleccionarProductos> productosSeleccionados;

    public CarritoCompras() {}

    public CarritoCompras(Cliente clienteModel) {
        this.precioTotal = 0; // Inicializa en cero
        this.clienteModel = clienteModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Cliente getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(Cliente clienteModel) {
        this.clienteModel = clienteModel;
    }

    public List<SeleccionarProductos> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setProductosSeleccionados(List<SeleccionarProductos> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }
}
