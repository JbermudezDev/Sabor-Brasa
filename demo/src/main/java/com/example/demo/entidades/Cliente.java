package com.example.demo.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String telefono;
    private String direccion;

      @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Cliente() {}

    public Cliente(String nombre, String apellido, String correo, String contraseña, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.direccion = direccion;
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

    public String getApellido() { 
        return apellido;
     }
    public void setApellido(String apellido) { 
        this.apellido = apellido; 
    }

    public String getCorreo() { 
        return correo; 
    }
    public void setCorreo(String correo) { 
        this.correo = correo;
     }

    public String getContraseña() { 
        return contraseña;
     }
    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña;
     }

    public String getTelefono() {
         return telefono;
     }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }


    public String getDireccion() {
         return direccion; 
    }
    public void setDireccion(String direccion) {
         this.direccion = direccion; 
    }
}