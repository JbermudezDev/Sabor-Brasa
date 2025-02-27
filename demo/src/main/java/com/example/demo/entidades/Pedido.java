package com.example.demo.entidades;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    
    private String estado;

    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    public Pedido() {}

    public Pedido(Date fechaCreacion, String estado, Cliente cliente) {
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) {
         this.id = id; 
        }

    public Date getFechaCreacion() { 
        return fechaCreacion;
     }
    public void setFechaCreacion(Date fechaCreacion) { 
        this.fechaCreacion = fechaCreacion;
     }

    public Date getFechaEntrega() { 
        return fechaEntrega; 
    }
    public void setFechaEntrega(Date fechaEntrega) { 
        this.fechaEntrega = fechaEntrega;
     }

    public String getEstado() {
         return estado; 
        }
    public void setEstado(String estado) {
        this.estado = estado; 
    }

    public Cliente getCliente() { 
        return cliente; 
    }
    public void setCliente(Cliente cliente) {
         this.cliente = cliente;
         }
}