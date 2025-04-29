package com.example.demo.repositorio;

import com.example.demo.entidades.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Integer> {

    CarritoCompras findByClienteModelId(Integer clienteId);   // <---- AGREGA ESTE MÉTODO
}
