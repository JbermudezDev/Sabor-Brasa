package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "seleccionar_productos")
public class SeleccionarProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private CarritoCompras carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToMany
    @JoinTable(
        name = "producto_seleccionado_adicionales",
        joinColumns = @JoinColumn(name = "producto_seleccionado_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionales;

   

    public SeleccionarProductos(Integer id, int cantidad, CarritoCompras carrito, Producto producto, List<Adicional> adicionales) {
        this.id = id;
        this.cantidad = cantidad;
        this.carrito = carrito;
        this.producto = producto;
        this.adicionales = adicionales;
    }

}
