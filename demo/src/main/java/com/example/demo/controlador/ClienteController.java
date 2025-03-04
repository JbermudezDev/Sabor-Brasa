package com.example.demo.controlador;

import com.example.demo.entidades.Cliente;
import com.example.demo.servicio.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public String mostrarClientes(Model model) {
        List<Cliente> clientes = clienteService.searchAll();
        model.addAttribute("clientes", clientes);
        return "ListadoClientes";
    }

    @PostMapping("/add")
    public String agregarCliente(@ModelAttribute Cliente cliente) {
        clienteService.add(cliente);
        return "redirect:/clientes/all";
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/all";
    }

    @PutMapping("/update/{id}")
    public String modificarCliente(@ModelAttribute Cliente cliente, @PathVariable("id") Long id) {
        cliente.setId(id);
        clienteService.update(cliente);
        return "redirect:/clientes/all";
    }
}