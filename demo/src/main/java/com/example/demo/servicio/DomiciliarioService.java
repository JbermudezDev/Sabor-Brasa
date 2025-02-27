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

    public List<Domiciliario> listarTodos() {
        return domiciliarioRepository.findAll();
    }

    public Optional<Domiciliario> buscarPorId(Long id) {
        return domiciliarioRepository.findById(id);
    }

    public Domiciliario guardar(Domiciliario domiciliario) {
        return domiciliarioRepository.save(domiciliario);
    }

    public void eliminarPorId(Long id) {
        domiciliarioRepository.deleteById(id);
    }
}