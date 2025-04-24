package com.example.demo.repositorio;

import com.example.demo.entidades.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Integer> {
    boolean existsByUsuario(String usuario);
    Optional<Operador> findByUsuarioAndContrasena(String usuario, String contrasena);
}
