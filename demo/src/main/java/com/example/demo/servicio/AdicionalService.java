package com.example.demo.servicio;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Producto;
import com.example.demo.repositorio.AdicionalRepository;
import com.example.demo.repositorio.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdicionalService {

    @Autowired
    private AdicionalRepository adicionalRepository;
    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los adicionales
    public List<Adicional> findAll() {
        return adicionalRepository.findAll(); 
    }
   
   
    // Obtener un adicional por ID
    public Optional<Adicional> findById(Long id) {
        return adicionalRepository.findById(id);
    }
    
    public void updateProductos(List<Producto> productos) {
        productos.forEach(producto -> productoRepository.save(producto));
    }

    // Agregar un nuevo adicional
    public void add(Adicional adicional) {
        adicionalRepository.save(adicional);
    }

    // Eliminar un adicional por ID
    public void deleteById(Long id) {
        adicionalRepository.deleteById(id);
    }

    // Actualizar un adicional
    public void update(Adicional adicional) {
        adicionalRepository.save(adicional);
    }

    public List<Adicional> findByIds(List<Long> ids) {
        return adicionalRepository.findAllById(ids);
    }

   
}