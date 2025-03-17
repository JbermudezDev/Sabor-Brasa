package com.example.demo.servicio;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.repositorio.DomiciliarioRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DomiciliarioService {
    
    @Autowired
    private DomiciliarioRepository domiciliarioRepository;
    
    public List<Domiciliario> listarDomiciliarios(){
        return domiciliarioRepository.findAll();
    }
   
}