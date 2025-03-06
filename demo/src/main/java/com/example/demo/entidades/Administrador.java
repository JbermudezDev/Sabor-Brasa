package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String email; // Cambiado de 'usuario' a 'email'
    private String password; // Cambiado de 'contraseña' a 'password'

    // Constructor vacío
    public Administrador() {}

    // Constructor
    public Administrador(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
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

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) {
        this.password = password;
    }
}