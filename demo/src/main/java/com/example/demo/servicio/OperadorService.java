package com.example.demo.servicio;

import com.example.demo.entidades.Operador;
import com.example.demo.repositorio.OperadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {
    private final OperadorRepository operadorRepository;

    public OperadorService(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

   
}