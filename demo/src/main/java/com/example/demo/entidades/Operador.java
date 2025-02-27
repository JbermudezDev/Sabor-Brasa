package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Operador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String usuario;
    private String contraseña;

    public Operador() {}

    public Operador(String nombre, String usuario, String contraseña) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
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

    public String getUsuario() { 
        return usuario; 
    }
    public void setUsuario(String usuario) {
         this.usuario = usuario; 
    }

    public String getContraseña() { 
        return contraseña; 
    }
    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña; 
    }
}
