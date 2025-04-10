package com.example.demo.repositorio;

import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
 
    List<Producto> findByCategoria(Categoria categoria);

    // Metodo correcto para traer un producto con sus adicionales seleccionados
    @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.adicionales WHERE p.id = :id")
    Optional<Producto> findByIdWithAdicionales(@Param("id") Long id);
    
    
}