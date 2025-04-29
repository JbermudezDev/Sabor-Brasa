package com.example.demo.repositorio;

import com.example.demo.entidades.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Integer> {
}
