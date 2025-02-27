package com.example.demo.repositorio;
import com.example.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para Pedido
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}