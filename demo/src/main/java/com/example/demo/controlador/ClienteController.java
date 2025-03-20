package com.example.demo.controlador;

import com.example.demo.entidades.Cliente;
import com.example.demo.servicio.ClienteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/view/{id}")
    public String verCliente(@PathVariable("id") Long id, Model model) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "VerCliente";
        } else {
            return "redirect:/clientes/all";
        }
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "AgregarCliente";
    }

    @PostMapping("/add")
    public String agregarCliente(@ModelAttribute Cliente cliente) {
        clienteService.add(cliente);
        return "redirect:/clientes/all";
    }

    @PostMapping("/delete/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/all";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "EditarCliente";
        } else {
            return "redirect:/clientes/all";
        }
    }

    @PostMapping("/update/{id}")
    public String modificarCliente(@ModelAttribute Cliente cliente, @PathVariable("id") Long id) {
        cliente.setId(id);
        clienteService.update(cliente);
        return "redirect:/clientes/all";
    }
    @GetMapping("/perfil/{id}")
public String mostrarPerfilCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
    // Verifica si el cliente está autenticado
    Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
    if (cliente == null || !cliente.getId().equals(id)) {
        return "redirect:/loginCliente"; // Redirige al login si no está autenticado
    }

    // Carga los datos del cliente y los pasa al modelo
    Optional<Cliente> clienteOpt = clienteService.findById(id);
    if (clienteOpt.isPresent()) {
        model.addAttribute("cliente", clienteOpt.get());
        return "PerfilCliente"; // Muestra la página del perfil
    } else {
        return "redirect:/clientes/all"; // Redirige si no se encuentra el cliente
    }
}
@PostMapping("/perfil/update/{id}")
public String actualizarPerfilCliente(@PathVariable("id") Long id, @ModelAttribute Cliente cliente, HttpSession session) {
    // Verifica si el cliente está autenticado
    Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
    if (clienteLogueado == null || !clienteLogueado.getId().equals(id)) {
        return "redirect:/loginCliente"; // Redirige al login si no está autenticado
    }

    // Actualiza los datos del cliente
    cliente.setId(id);
    clienteService.update(cliente);

    // Actualiza la sesión con los nuevos datos
    session.setAttribute("clienteLogueado", cliente);

    // Redirige a la página de Cliente
    return "redirect:/Cliente/" + id;
}
   
}
