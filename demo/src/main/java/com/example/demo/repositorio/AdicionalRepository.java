package com.example.demo.repositorio;

import com.example.demo.entidades.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long> {
    
}