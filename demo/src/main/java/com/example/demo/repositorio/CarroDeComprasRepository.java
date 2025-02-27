package com.example.demo.repositorio;
import com.example.demo.entidades.CarroDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarroDeComprasRepository extends JpaRepository<CarroDeCompras, Long> {
}