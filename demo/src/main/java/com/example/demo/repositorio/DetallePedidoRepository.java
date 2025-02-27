package com.example.demo.repositorio;
import com.example.demo.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para DetallePedido
@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}