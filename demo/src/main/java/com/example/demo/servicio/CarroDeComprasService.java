package com.example.demo.servicio;

import com.example.demo.entidades.CarroDeCompras;
import com.example.demo.repositorio.CarroDeComprasRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class CarroDeComprasService {

    @Autowired
    private CarroDeComprasRepository carroDeComprasRepository;

   
}