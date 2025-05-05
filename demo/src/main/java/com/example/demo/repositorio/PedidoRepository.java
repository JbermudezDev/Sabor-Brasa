package com.example.demo.repositorio;

import com.example.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCarritoClienteId(Long id); 


    List<Pedido> findByEstado(String estado);

    List<Pedido> findByOperadorId(Integer operadorId);
}
