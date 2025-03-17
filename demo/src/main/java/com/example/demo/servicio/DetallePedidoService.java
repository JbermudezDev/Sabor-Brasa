package com.example.demo.servicio;

import com.example.demo.entidades.DetallePedido;
import com.example.demo.repositorio.DetallePedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
   
}