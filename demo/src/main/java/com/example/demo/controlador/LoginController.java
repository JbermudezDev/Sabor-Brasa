package com.example.demo.controlador;

import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Operador;
import com.example.demo.entidades.CarritoCompras;
import com.example.demo.servicio.AdministradorService;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.OperadorService;
import com.example.demo.servicio.CarritoComprasService;
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

    //http://localhost:8090/login/cliente
    // ====== LOGIN CLIENTE CON CREACIÓN DE COOKIE ======
    @PostMapping("/cliente")
    public ResponseEntity<?> loginCliente(@RequestBody Cliente credenciales, HttpServletResponse response) {
        String email = credenciales.getEmail();
        String password = credenciales.getPassword();

        Cliente cliente = clienteService.autenticar(email, password);

        if (cliente != null) {
            // Buscar si ya existe un carrito
            CarritoCompras carrito = carritoService.buscarCarritoPorCliente(cliente.getId());
            if (carrito == null) {
                carrito = carritoService.crearCarrito(cliente.getId()); // Crear si no existe
            }

            // Crear cookie para clienteId
            Cookie clienteCookie = new Cookie("clienteId", String.valueOf(cliente.getId()));
            clienteCookie.setHttpOnly(false);
            clienteCookie.setSecure(false); // Para localhost puede ser false
            clienteCookie.setMaxAge(60 * 60); // 1 hora
            clienteCookie.setPath("/");
            response.addCookie(clienteCookie);

            // Crear cookie para carritoId
            Cookie carritoCookie = new Cookie("carritoId", String.valueOf(carrito.getId()));
            carritoCookie.setHttpOnly(false);
            carritoCookie.setSecure(false);
            carritoCookie.setMaxAge(60 * 60);
            carritoCookie.setPath("/");
            response.addCookie(carritoCookie);

            return ResponseEntity.ok(cliente); // Retorna el cliente
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    //http://localhost:8090/login/admin
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

    //http://localhost:8090/login/operador
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

    //http://localhost:8090/login/logoutCliente
    // ====== LOGOUT CLIENTE ======
    @PostMapping("/logoutCliente")
    public ResponseEntity<?> logoutCliente(HttpServletResponse response) {
        // Eliminar cookies
        Cookie clienteCookie = new Cookie("clienteId", null);
        clienteCookie.setMaxAge(0);
        clienteCookie.setPath("/");
        response.addCookie(clienteCookie);

        Cookie carritoCookie = new Cookie("carritoId", null);
        carritoCookie.setMaxAge(0);
        carritoCookie.setPath("/");
        response.addCookie(carritoCookie);

        return ResponseEntity.ok("Sesión de cliente cerrada correctamente");
    }
}
