package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;


@Entity
@Table(name = "adicionales")
public class Adicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String nombre;
    private double precio;
    private String descripcion;
   
    @ManyToMany(mappedBy = "adicionales")
    private List<Producto> productos = new ArrayList<>();

    public Adicional() {}

    public Adicional( String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
        producto.getAdicionales().add(this);
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

    public double getPrecio() { 
        return precio; 
    }
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public String getDescripcion() { 
        return descripcion;
     }
    public void setDescripcion(String descripcion) {
         this.descripcion = descripcion;
         }

    public  List<Producto> getProductos() {
         return productos;
         }
    public void setProductos(List<Producto> productos) { 
        this.productos = productos; 
    }

    }

    