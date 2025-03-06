package com.example.demo.servicio;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.repositorio.DomiciliarioRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DomiciliarioService {
    private final DomiciliarioRepository domiciliarioRepository;

    public DomiciliarioService(DomiciliarioRepository domiciliarioRepository) {
        this.domiciliarioRepository = domiciliarioRepository;
    }

   
}