package com.example.demo.entidades;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Domiciliario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String celular;
    private String cedula;
    private boolean disponible;

    public Domiciliario() {}

    public Domiciliario(String nombre, String celular, String cedula, boolean disponible) {
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
        this.disponible = disponible;
    }

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

    public boolean isDisponible() { 
        return disponible;
     }
    public void setDisponible(boolean disponible) {
         this.disponible = disponible;
    }
}