package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
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

    public Pedido() {}

    public Pedido(Integer id, EstadoPedido estado, Date fechaCreacion, Date fechaEntrega,
                  Operador operador, Domiciliario domiciliario, CarritoCompras carrito) {
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.operador = operador;
        this.domiciliario = domiciliario;
        this.carrito = carrito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
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

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Domiciliario getDomiciliario() {
        return domiciliario;
    }

    public void setDomiciliario(Domiciliario domiciliario) {
        this.domiciliario = domiciliario;
    }

    public CarritoCompras getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoCompras carrito) {
        this.carrito = carrito;
    }
}
