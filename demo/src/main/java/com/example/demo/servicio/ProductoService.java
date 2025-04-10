package com.example.demo.servicio;

import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.Producto;
import com.example.demo.repositorio.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

   // Obtener todos los productos
   public List<Producto> searchAll() {
    return productoRepository.findAll(); // Devuelve todos los registros
}

  public Optional<Producto> findById(Long id) {
    return productoRepository.findByIdWithAdicionales(id);
  }

  public Producto add(Producto producto) {
    return productoRepository.save(producto);
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

  public void update(Producto producto) {
    productoRepository.save(producto);
  }
}