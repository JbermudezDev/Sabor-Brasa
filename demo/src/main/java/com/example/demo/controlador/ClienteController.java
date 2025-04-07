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

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/all")
    public List<Cliente> mostrarClientes(Model model) {
        return clienteService.searchAll();
    }

    @GetMapping("/find/{id}")
    public Cliente verCliente(@PathVariable("id") Long id) {
            return clienteService.findById(id).orElse(null);
    }       


    
    
    
    @GetMapping("/agregar")
    public String mostrarFormularioAgregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "AgregarCliente";
    }

    @PostMapping("/add")
    public void agregarCliente(@RequestBody Cliente cliente) {
        clienteService.add(cliente);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        
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

    @PutMapping("/update/{id}")
    public void modificarCliente(@RequestBody Cliente cliente) {
        clienteService.update(cliente);
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
