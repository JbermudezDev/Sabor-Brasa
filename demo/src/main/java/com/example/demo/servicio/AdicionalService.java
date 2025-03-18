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

    // Metodo para obtener todos los adicionales
   public List<Adicional> findAll() {
        return adicionalRepository.findAll();
    }
    
    public List<Adicional> findByIds(List<Long> ids) {
        return adicionalRepository.findAllById(ids);
    }

    // Método para agregar un adicional
    public void add(Adicional adicional) {
        adicionalRepository.save(adicional); // Guardar el adicional en la base de datos
    }

    // Método para eliminar un adicional por su ID
    public void deleteById(Long id) {
       Optional<Adicional> adicionalOptional = adicionalRepository.findById(id);
    
    if (adicionalOptional.isPresent()) {
        Adicional adicional = adicionalOptional.get();

        // Primero eliminar la relación en la tabla intermedia
        for (Producto producto : adicional.getProductos()) {
            producto.getAdicionales().remove(adicional);
        }

        // Guardar los productos sin el adicional
        productoRepository.saveAll(adicional.getProductos());

        // Ahora sí eliminar el adicional
        adicionalRepository.delete(adicional);
    }
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