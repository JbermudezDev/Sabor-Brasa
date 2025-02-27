package com.example.demo.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
public class CarroDeCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // existing fields and methods

   
    @OneToOne
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    public CarroDeCompras() {}

    public CarroDeCompras(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}