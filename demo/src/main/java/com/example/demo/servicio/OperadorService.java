package com.example.demo.servicio;

import com.example.demo.entidades.Cliente;
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

    public Operador add(Operador operador) {
        return operadorRepository.save(operador);
    }

    
    
    public boolean existsByUsuario(String usuario) {
        return operadorRepository.existsByUsuario(usuario);
    }
   
    public void update(Operador operador) {
        operadorRepository.save(operador);
    }

    public void deleteById(Integer id) {
        operadorRepository.deleteById(id);
    }

    public Operador autenticar(String usuario, String contrasena) {
        return operadorRepository.findByUsuarioAndContrasena(usuario, contrasena).orElse(null);
    }

    public Optional<Operador> buscarPorUsuario(String usuario) {
    return operadorRepository.findByUsuario(usuario);
}

}