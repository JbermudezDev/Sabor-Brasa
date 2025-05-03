package com.example.demo.repositorio;

import com.example.demo.entidades.SeleccionarProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeleccionarProductosRepository extends JpaRepository<SeleccionarProductos, Integer> {
    List<SeleccionarProductos> findByCarritoId(Integer carritoId);
}
