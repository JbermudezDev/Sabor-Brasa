package com.example.demo.controlador;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.DTO.ClienteMapper;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.servicio.ClienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController // Cambiado a RestController para manejar JSON
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200") // Cambiado para permitir solicitudes desde Angular
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private com.example.demo.repositorio.ClienteRepository clienteRepository;

  @Autowired
  private UserRepository userRepository;

  

  //http://localhost:8090/clientes/all
  @GetMapping("/all") // Cambiado a /all para evitar conflictos con el método mostrarClientes
  public List<Cliente> mostrarClientes(Model model) {
    return clienteService.searchAll();
  }

  //http://localhost:8090/clientes/find/id
  @GetMapping("/find/{id}") // Cambiado a /find/{id} para evitar conflictos con el método verCliente
  public Cliente verCliente(@PathVariable("id") Long id) {
    return clienteService.findById(id).orElse(null);
  }
  
  //http://localhost:8090/clientes/agregar
  @GetMapping("/agregar")
  public String mostrarFormularioAgregarCliente(Model model) {
    model.addAttribute("cliente", new Cliente());
    return "AgregarCliente";
  }

  //http://localhost:8090/clientes/add
@PostMapping("/add")
public ResponseEntity<ClienteDTO> agregarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
    
    // Validación de existencia del cliente (si aplica)
    if (clienteRepository.findByEmail(clienteDTO.getEmail()) != null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Convertir de DTO a entidad
    Cliente cliente = ClienteMapper.INSTANCE.convert(clienteDTO);

    // Guardar el cliente
    Cliente nuevoCliente = clienteService.add(cliente);

    // Convertir de vuelta a DTO
    ClienteDTO nuevoClienteDTO = ClienteMapper.INSTANCE.convert(nuevoCliente);

    if (nuevoCliente == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
}


  //http://localhost:8090/clientes/delete/id
  @DeleteMapping("/delete/{id}") // Cambiado a /delete/{id} para evitar conflictos con el método eliminarCliente
  public void eliminarCliente(@PathVariable("id") Long id) {
    clienteService.deleteById(id);
  }

  //http://localhost:8090/clientes/update/id
  @GetMapping("/update/{id}")
  public String mostrarFormularioEdicion(
    @PathVariable("id") Long id,
    Model model
  ) {
    Optional<Cliente> cliente = clienteService.findById(id);
    if (cliente.isPresent()) {
      model.addAttribute("cliente", cliente.get());
      return "EditarCliente";
    } else {
      return "redirect:/clientes/all";
    }
  }

  //http://localhost:8090/clientes/update/id
  @PutMapping("/update/{id}") // Cambiado a /update/{id} para evitar conflictos con el método modificarCliente
  public void modificarCliente(@RequestBody Cliente cliente) {
    clienteService.update(cliente);
  }

  //http://localhost:8090/clientes/perfil/id
  @GetMapping("/perfil/{id}")
  public String mostrarPerfilCliente(
    @PathVariable("id") Long id,
    Model model,
    HttpSession session
  ) {
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

  //http://localhost:8090/clientes/perfil/update/id
  @PostMapping("/perfil/update/{id}")
  public String actualizarPerfilCliente(
    @PathVariable("id") Long id,
    @ModelAttribute Cliente cliente,
    HttpSession session
  ) {
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