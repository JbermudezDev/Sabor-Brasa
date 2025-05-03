package com.example.demo.repositorio;

import com.example.demo.entidades.Domiciliario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomiciliarioRepository extends JpaRepository<Domiciliario, Integer> {
    List<Domiciliario> findByDisponibilidadTrue();
}