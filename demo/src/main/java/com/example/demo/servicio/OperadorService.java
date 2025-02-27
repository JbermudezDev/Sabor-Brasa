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

    public List<Operador> listarTodos() {
        return operadorRepository.findAll();
    }

    public Optional<Operador> buscarPorId(Long id) {
        return operadorRepository.findById(id);
    }

    public Operador guardar(Operador operador) {
        return operadorRepository.save(operador);
    }

    public void eliminarPorId(Long id) {
        operadorRepository.deleteById(id);
    }
}