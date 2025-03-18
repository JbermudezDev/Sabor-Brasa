package com.example.demo.repositorio;
import com.example.demo.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
     Administrador findByEmailAndPassword(String email, String password);
}
