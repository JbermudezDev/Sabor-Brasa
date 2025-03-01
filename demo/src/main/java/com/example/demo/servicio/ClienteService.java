package com.example.demo.servicio;

import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
   @Autowired
    private ClienteRepository clienteRepository;

    public Cliente autenticar(String email, String password) {
        return clienteRepository.findByEmailAndPassword(email, password);
    }

}