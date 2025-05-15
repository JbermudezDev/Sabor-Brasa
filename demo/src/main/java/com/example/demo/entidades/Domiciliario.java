package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

  

    public Domiciliario(Integer id, String nombre, String celular, String cedula, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
        this.disponibilidad = disponibilidad;
    }

  
}
