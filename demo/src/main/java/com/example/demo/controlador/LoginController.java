package com.example.demo.controlador;

import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Operador;
import com.example.demo.servicio.AdministradorService;
import com.example.demo.servicio.ClienteService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import com.example.demo.servicio.OperadorService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class LoginController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OperadorService operadorService;

    // Muestra formulario de login del cliente (sin cambios, ya que es para vistas)
    @GetMapping("/loginCliente")
    public String mostrarFormularioLoginCliente() {
        return "InicioSesion"; 
    }

    // Autenticación del Cliente (sin cambios, ya que es para vistas)
    @PostMapping("/loginCliente")
    public String loginCliente(@RequestParam String email, 
                               @RequestParam String password, 
                               Model model, 
                               HttpSession session) {
        Cliente cliente = clienteService.autenticar(email, password);

        if (cliente != null) {
            session.setAttribute("clienteLogueado", cliente);
            return "redirect:/Cliente/" + cliente.getId(); // Redirige con el ID en la URL
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "InicioSesion"; 
        }
    }

    // Página del Cliente (sin cambios, ya que es para vistas)
    @GetMapping("/Cliente/{id}")
    public String mostrarPaginaCliente(@PathVariable Long id, Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null || !cliente.getId().equals(id)) {
            return "redirect:/loginCliente"; // Redirige al login si no está autenticado
        }

        model.addAttribute("cliente", cliente);
        return "Cliente"; 
    }

    // Autenticación del Administrador (modificado para Angular)
    @PostMapping("/admin")
    public ResponseEntity<?> loginAdministrador(@RequestBody Administrador credenciales, HttpSession session) {
        // Extraer email y password del objeto recibido
        String email = credenciales.getEmail();
        String password = credenciales.getPassword();
    
        // Autenticar al administrador
        Administrador admin = administradorService.autenticar(email, password);
    
        if (admin != null) {
            session.setAttribute("adminLogueado", admin); // Guardar administrador en la sesión
            return ResponseEntity.ok(admin); // Devuelve el administrador autenticado en formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/operador")
    public ResponseEntity<?> loginOperador(@RequestBody Operador credenciales, HttpSession session) {
    // Extraer usuario y contraseña del objeto recibido
    String usuario = credenciales.getUsuario();
    String contrasena = credenciales.getContrasena();

    // Autenticar al operador
    Operador operador = operadorService.autenticar(usuario, contrasena);

    if (operador != null) {
        session.setAttribute("operadorLogueado", operador); // Guardar operador en la sesión
        return ResponseEntity.ok(operador); // Devuelve el operador autenticado en formato JSON
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
    }

    // Cerrar sesión del Administrador (nuevo método para Angular)
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Invalida la sesión
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    // Página del Administrador (sin cambios, ya que es para vistas)
    @GetMapping("/Administrador")
    public String mostrarPaginaAdministrador() {
        return "Administrador"; 
    }
}