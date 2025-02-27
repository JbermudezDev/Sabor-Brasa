package com.example.demo.controlador;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entidades.CarroDeCompras;
import com.example.demo.servicio.CarroDeComprasService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro-de-compras")
public class CarroDeComprasController {
    private final CarroDeComprasService carroDeComprasService;

    public CarroDeComprasController(CarroDeComprasService carroDeComprasService) {
        this.carroDeComprasService = carroDeComprasService;
    }

    @GetMapping
    public List<CarroDeCompras> listarTodos() {
        return carroDeComprasService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CarroDeCompras> buscarPorId(@PathVariable Long id) {
        return carroDeComprasService.buscarPorId(id);
    }

    @PostMapping
    public CarroDeCompras crear(@RequestBody CarroDeCompras carroDeCompras) {
        return carroDeComprasService.guardar(carroDeCompras);
    }

    @PutMapping("/{id}")
    public CarroDeCompras actualizar(@PathVariable Long id, @RequestBody CarroDeCompras carroDeCompras) {
        carroDeCompras.setId(id);
        return carroDeComprasService.guardar(carroDeCompras);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        carroDeComprasService.eliminarPorId(id);
    }
}