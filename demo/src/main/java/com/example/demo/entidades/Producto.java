package com.example.demo.entidades;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private float precio;
    private String descripcion;
    private String imagen;

    @ManyToMany
    @JoinTable(
        name = "producto_adicional", 
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private Set<Adicional> adicionales;

    public Producto() {}

    public Producto(String nombre, float precio, String descripcion, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.adicionales = new HashSet<>();
    }

    public void agregarAdicional(Adicional adicional) {
        this.adicionales.add(adicional);
    }

    // Getters y Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id;
     }

    public String getNombre() { 
        return nombre;
     }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public float getPrecio() { 
        return precio;
     }
    public void setPrecio(float precio) { 
        this.precio = precio;
     }

    public String getDescripcion() { 
        return descripcion;
     }
    public void setDescripcion(String descripcion) {
         this.descripcion = descripcion;
         }

    public String getImagen() {
         return imagen; 
        }
    public void setImagen(String imagen) {
         this.imagen = imagen;
         }

    public Set<Adicional> getAdicionales() { 
        return adicionales;
     }
    public void setAdicionales(Set<Adicional> adicionales) {
         this.adicionales = adicionales;
         }
}
