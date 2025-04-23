package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "domiciliario")
public class Domiciliario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String celular;

    @Column(unique = true)
    private String cedula;

    @Column(nullable = false)
    private boolean disponibilidad;

    @OneToMany(mappedBy = "domiciliario", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Pedido> pedidos;

    public Domiciliario() {
    }

    public Domiciliario(Integer id, String nombre, String celular, String cedula, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
        this.disponibilidad = disponibilidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
