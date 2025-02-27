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

    public List<CarroDeCompras> listarTodos() {
        return carroDeComprasRepository.findAll();
    }

    public Optional<CarroDeCompras> buscarPorId(Long id) {
        return carroDeComprasRepository.findById(id);
    }

    public CarroDeCompras guardar(CarroDeCompras carroDeCompras) {
        return carroDeComprasRepository.save(carroDeCompras);
    }

    public void eliminarPorId(Long id) {
        carroDeComprasRepository.deleteById(id);
    }
}