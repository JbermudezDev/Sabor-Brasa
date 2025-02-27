package com.example.demo.repositorio;
import com.example.demo.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para Producto
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}