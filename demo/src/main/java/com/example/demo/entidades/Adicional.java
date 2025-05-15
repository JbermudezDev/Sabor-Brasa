package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


@Entity
@Data
@NoArgsConstructor

@Table(name = "adicional")
public class Adicional {

  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private float precio;
    private String descripcion;

    @ManyToMany(mappedBy = "adicionales")
    @JsonBackReference
    private List<Producto> productos = new ArrayList<>();



    // Constructor con parámetros
    public Adicional(String nombre, float precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

}

    