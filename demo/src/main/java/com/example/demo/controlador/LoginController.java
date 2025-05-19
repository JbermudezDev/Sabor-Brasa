package com.example.demo.controlador;

import com.example.demo.Security.JWTGenerator;
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

import java.net.Authenticator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Autowired
    private org.springframework.security.authentication.AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;
    //http://localhost:8090/login/cliente
    // ====== LOGIN CLIENTE CON CREACIÓN DE COOKIE ======
   @PostMapping("/cliente")
public ResponseEntity<?> loginCliente(@RequestBody Cliente credenciales) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credenciales.getEmail(), credenciales.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        Cliente clienteAutenticado = clienteService.autenticar(credenciales.getEmail(), credenciales.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("cliente", clienteAutenticado);

        return ResponseEntity.ok(response); // ✅ Devuelve: { "token": "...", "cliente": {...} }

    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
    }
}


    //http://localhost:8090/login/admin
    // ====== LOGIN ADMINISTRADOR ======
    @PostMapping("/admin")
    public ResponseEntity<?> loginAdministrador(@RequestBody Administrador credenciales) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credenciales.getEmail(), credenciales.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    //http://localhost:8090/login/operador
    // ====== LOGIN OPERADOR ======
   @PostMapping("/operador")
public ResponseEntity<?> loginOperador(@RequestBody Operador credenciales) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credenciales.getUsuario(), credenciales.getContrasena())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response); // ✅ Devuelve JSON: { "token": "..." }

    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
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
