package com.example.demo.servicio;

import com.example.demo.entidades.Domiciliario;
import com.example.demo.repositorio.DomiciliarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomiciliarioService {

    @Autowired
    private DomiciliarioRepository domiciliarioRepository;

    public List<Domiciliario> obtenerTodos() {
        return domiciliarioRepository.findAll();
    }

    public List<Domiciliario> obtenerDisponibles() {
        return domiciliarioRepository.findByDisponibilidadTrue();
    }

    public Optional<Domiciliario> buscarPorId(Integer id) {
        return domiciliarioRepository.findById(id);
    }

    public Domiciliario guardar(Domiciliario d) {
        return domiciliarioRepository.save(d);
    }

    public void eliminar(Integer id) {
        domiciliarioRepository.deleteById(id);
    }
}