package com.example.demo.servicio;

import com.example.demo.entidades.CarritoCompras;
import com.example.demo.entidades.SeleccionarProductos;
import com.example.demo.repositorio.CarritoComprasRepository;
import com.example.demo.repositorio.SeleccionarProductosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoComprasService {

  @Autowired
  private CarritoComprasRepository carritoComprasRepository;

  public CarritoCompras guardar(CarritoCompras carrito) {
    return carritoComprasRepository.save(carrito);
  }
}
