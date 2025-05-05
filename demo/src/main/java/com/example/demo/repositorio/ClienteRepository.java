package com.example.demo.repositorio;
import com.example.demo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Repositorio para Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
      Cliente findByEmailAndPassword(String email, String password);
      Cliente findByNombre(String nombre);
      Cliente findByEmail(String email);

      Cliente findByNombreAndApellido(String nombre, String apellido);
      List<Cliente> findByDireccionContainingIgnoreCase(String direccion);
      Cliente findByTelefono(String telefono);



      
}
