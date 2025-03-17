package com.example.demo.servicio;

import com.example.demo.entidades.Pedido;
import com.example.demo.repositorio.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


}