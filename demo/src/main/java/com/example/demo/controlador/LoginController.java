package com.example.demo.controlador;

import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Operador;
import com.example.demo.servicio.AdministradorService;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.OperadorService;
import com.example.demo.servicio.CarritoComprasService;
import com.example.demo.entidades.CarritoCompras;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private CarritoComprasService carritoService;

    // ====== LOGIN CLIENTE USANDO COOKIES =======
    @PostMapping("/cliente")
    public ResponseEntity<?> loginCliente(@RequestBody Cliente credenciales, HttpServletResponse response) {
        String email = credenciales.getEmail();
        String password = credenciales.getPassword();

        Cliente cliente = clienteService.autenticar(email, password);

        if (cliente != null) {
            // Crear cookie para clienteId
            Cookie clienteCookie = new Cookie("clienteId", String.valueOf(cliente.getId()));
            clienteCookie.setHttpOnly(true);
            clienteCookie.setSecure(true);
            clienteCookie.setMaxAge(60 * 60); // 1 hora
            clienteCookie.setPath("/");
            response.addCookie(clienteCookie);

            // Crear carrito para el cliente
            CarritoCompras carrito = carritoService.crearCarrito(cliente.getId());

            // Crear cookie para carritoId
            Cookie carritoCookie = new Cookie("carritoId", String.valueOf(carrito.getId()));
            carritoCookie.setHttpOnly(true);
            carritoCookie.setSecure(true);
            carritoCookie.setMaxAge(60 * 60 * 24); // 24 horas
            carritoCookie.setPath("/");
            response.addCookie(carritoCookie);

            return ResponseEntity.ok(cliente); // Devuelve el cliente autenticado
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // ====== LOGIN ADMINISTRADOR ======
    @PostMapping("/admin")
    public ResponseEntity<?> loginAdministrador(@RequestBody Administrador credenciales, HttpSession session) {
        String email = credenciales.getEmail();
        String password = credenciales.getPassword();

        Administrador admin = administradorService.autenticar(email, password);

        if (admin != null) {
            session.setAttribute("adminLogueado", admin);
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // ====== LOGIN OPERADOR ======
    @PostMapping("/operador")
    public ResponseEntity<?> loginOperador(@RequestBody Operador credenciales, HttpSession session) {
        String usuario = credenciales.getUsuario();
        String contrasena = credenciales.getContrasena();

        Operador operador = operadorService.autenticar(usuario, contrasena);

        if (operador != null) {
            session.setAttribute("operadorLogueado", operador);
            return ResponseEntity.ok(operador);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // ====== LOGOUT CLIENTE ======
    @PostMapping("/logoutCliente")
    public ResponseEntity<?> logoutCliente(HttpServletResponse response) {
        // Borrar cookies del cliente
        Cookie clienteCookie = new Cookie("clienteId", null);
        clienteCookie.setHttpOnly(true);
        clienteCookie.setSecure(true);
        clienteCookie.setMaxAge(0);
        clienteCookie.setPath("/");
        response.addCookie(clienteCookie);

        Cookie carritoCookie = new Cookie("carritoId", null);
        carritoCookie.setHttpOnly(true);
        carritoCookie.setSecure(true);
        carritoCookie.setMaxAge(0);
        carritoCookie.setPath("/");
        response.addCookie(carritoCookie);

        return ResponseEntity.ok("Sesión de cliente cerrada correctamente");
    }

    // ====== LOGOUT ADMIN Y OPERADOR ======
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }
}
