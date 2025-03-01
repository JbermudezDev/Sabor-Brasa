package com.example.demo.servicio;

import com.example.demo.entidades.CarroDeCompras;
import com.example.demo.repositorio.CarroDeComprasRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroDeComprasService {
    private final CarroDeComprasRepository carroDeComprasRepository;

    public CarroDeComprasService(CarroDeComprasRepository carroDeComprasRepository) {
        this.carroDeComprasRepository = carroDeComprasRepository;
    }

   
}