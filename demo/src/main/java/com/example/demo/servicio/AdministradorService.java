package com.example.demo.servicio;

import com.example.demo.entidades.Administrador;
import com.example.demo.repositorio.AdministradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    
     @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador autenticar(String email, String password) {
        return administradorRepository.findByEmailAndPassword(email, password);
    }
    

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador guardar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void eliminarPorId(Long id) {
        administradorRepository.deleteById(id);
    }
}