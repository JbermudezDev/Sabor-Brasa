package com.example.demo.servicio;

import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.Producto;
import com.example.demo.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> searchAll() {
        return productoRepository.findAll();
    }


    public Optional<Producto> findById(Long id) {
        return productoRepository.findByIdWithAdicionales(id);
    }

    public void add(Producto producto) {
        productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }




}