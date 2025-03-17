package com.example.demo.servicio;

import com.example.demo.entidades.Operador;
import com.example.demo.repositorio.OperadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

   
}