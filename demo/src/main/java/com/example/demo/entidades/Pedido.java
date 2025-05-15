package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = true) // Puede ser null hasta que se entregue
    private Date fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "operador_id")
    private Operador operador;

    @ManyToOne
    @JoinColumn(name = "domiciliario_id")
    @JsonManagedReference
    private Domiciliario domiciliario;

    @OneToOne
    @JoinColumn(name = "carrito_id", nullable = false, unique = true)
    @JsonManagedReference
    private CarritoCompras carrito;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private double total;

    

    public Pedido(Integer id, EstadoPedido estado, Date fechaCreacion, Date fechaEntrega,
                  Operador operador, Domiciliario domiciliario, CarritoCompras carrito, Cliente cliente, double total) {
        this.cliente = cliente;
        this.total = total;
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.operador = operador;
        this.domiciliario = domiciliario;
        this.carrito = carrito;
    }

   
}