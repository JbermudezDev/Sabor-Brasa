package com.example.demo.servicio;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.AdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdicionalService {

    @Autowired
    private AdicionalRepository adicionalRepository;

    // Metodo para obtener todos los adicionales
   public List<Adicional> searchAll() {
        return adicionalRepository.findAll();
    }

    // Método para agregar un adicional
    public void add(Adicional adicional) {
        adicionalRepository.save(adicional); // Guardar el adicional en la base de datos
    }

    // Método para eliminar un adicional por su ID
    public void deleteById(Long id) {
        adicionalRepository.deleteById(id); // Eliminar adicional por ID
    }

    // Método para actualizar un adicional
    public void update(Adicional adicional) {
        adicionalRepository.save(adicional); // Guardar el adicional actualizado
    }

    // Método para buscar un adicional por su ID
    public Optional<Adicional> findById(Long id) {
        return adicionalRepository.findById(id); // Buscar adicional por ID
    }
}