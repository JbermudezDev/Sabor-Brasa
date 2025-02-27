package com.example.demo.repositorio;
import com.example.demo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}