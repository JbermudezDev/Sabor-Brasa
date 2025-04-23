package com.example.demo.servicio;

import com.example.demo.entidades.Operador;
import com.example.demo.repositorio.OperadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    public List<Operador> searchAll() {
        return operadorRepository.findAll();
    }

    public Optional<Operador> findById(Integer id) {
        return operadorRepository.findById(id);
    }

    public void add(Operador operador) {
        operadorRepository.save(operador);
    }

    public void update(Operador operador) {
        operadorRepository.save(operador);
    }

    public void deleteById(Integer id) {
        operadorRepository.deleteById(id);
    }
}