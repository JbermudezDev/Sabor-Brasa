package com.example.demo.entidades;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import com.example.demo.entidades.Adicional;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;
    private String descripcion;
    private String imagen;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "producto_adicional",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionales = new ArrayList<>();
    

    public Producto() {}

    public Producto(String nombre, double precio, String descripcion, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public void addAdicional(Adicional adicional) {
        adicionales.add(adicional);
        adicional.getProductos().add(this);
    }
    public void removeAdicional(Adicional adicional) {
        adicionales.remove(adicional);
        adicional.getProductos().remove(this);
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

    public String getImagen() {
         return imagen; 
        }
    public void setImagen(String imagen) {
         this.imagen = imagen;
         }

         public Categoria getCategoria() {
            return categoria;
        }
    
        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }

    public List<Adicional> getAdicionales() {
            return adicionales;
        }
    
    public void setAdicionales(List<Adicional> adicionales) {
            this.adicionales = adicionales;
        }

   
}