package com.example.demo.repositorio;
import com.example.demo.entidades.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
}