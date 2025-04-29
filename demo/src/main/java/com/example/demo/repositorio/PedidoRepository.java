package com.example.demo.repositorio;

import com.example.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCarritoClienteModelId(Integer clienteId);
}
