package com.example.demo.entidades;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class CarroDeCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    // Constructor vacío
    public CarroDeCompras() {}

    // Constructor
    public CarroDeCompras(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}