package com.example.demo.repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.OperadorRepository;
import com.example.demo.entidades.Cliente;
import com.example.demo.repositorio.AdministradorRepository;
import com.example.demo.repositorio.CarritoComprasRepository;

import java.util.Optional;
import java.util.List;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ClienteRepositoryTest {
    

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private CarritoComprasRepository carritoComprasRepository;
    
    @Autowired
    private OperadorRepository operadorRepository;

    
    @Test
    public void ClienteRepository_save_cliente(){
        //1. arange
        Cliente cliente = new Cliente("Andres", "Angarita", "profesor123@gmail.com", "profe123", "1234567890", "Javeriana");
        //2. act
        Cliente savedCliente = clienteRepository.save(cliente);
        //3.assert
        Assertions.assertThat(savedCliente).isNotNull();
    }

    @Test
    public void ClienteRepository_FindAll_NotEmptyList(){
        //1. arange
        Cliente cliente = new Cliente("Julian", "Hernandez", "julian123@gmail.com", "julian123", "1234567890", "Javeriana U");
        Cliente cliente2 = new Cliente("Pedro", "Tovar", "pedro123@gmail.com", "pedro123", "1234567890", "Javeriana Pontificia");
        //2. act
        clienteRepository.save(cliente);
        clienteRepository.save(cliente2);
        List<Cliente> clientes = clienteRepository.findAll();
        //3.assert
        Assertions.assertThat(clientes).isNotNull();
        Assertions.assertThat(clientes.size()).isEqualTo(2);
        Assertions.assertThat(clientes.size()).isGreaterThan(0);
    }

    @Test
    public void ClienteRepository_findById_FindWrongIndex(){
        //1. arange        
        Long index = -1l;
        //2. act
       Optional<Cliente> cliente = clienteRepository.findById(index);
        //3.assert
       Assertions.assertThat(cliente).isEmpty();
    }

    @Test
    public void ClienteRepository_deleteById_EmptyCliente(){
        //1. arange
        Long index = 2l;

        //2. act
        clienteRepository.deleteById(index);

        //3.assert
        Assertions.assertThat(clienteRepository.findById(index)).isEmpty();
    }

    @Test
    public void ClienteRepository_findByNombre_Cliente() {
        // 1. Arrange
        String nombre = "Julian";
        Cliente cliente = new Cliente("Julian", "Hernandez", "julian123@gmail.com", "julian123", "1234567890", "Javeriana U");
        clienteRepository.save(cliente); // Guarda el cliente en la base de datos de prueba

        // 2. Act
        Cliente foundCliente = clienteRepository.findByNombre(nombre);

        // 3. Assert
        Assertions.assertThat(foundCliente).isNotNull();
        Assertions.assertThat(foundCliente.getNombre()).isEqualTo(nombre);
    }
       
@Test
public void ClienteRepository_updateNombre_Cliente() {
    // 1. Arrange
    String nombreOriginal = "Julian";
    String nuevoNombre = "Julian Actualizado";
    Cliente cliente = new Cliente("Julian", "Hernandez", "julian123@gmail.com", "julian123", "1234567890", "Javeriana U");
    clienteRepository.save(cliente); // Guarda el cliente en la base de datos de prueba

    // 2. Act
    Cliente clienteGuardado = clienteRepository.findByNombre(nombreOriginal);
    clienteGuardado.setNombre(nuevoNombre); // Actualiza el nombre
    clienteRepository.save(clienteGuardado); // Guarda los cambios

    // 3. Assert
    Cliente clienteActualizado = clienteRepository.findByNombre(nuevoNombre);
    Assertions.assertThat(clienteActualizado).isNotNull();
    Assertions.assertThat(clienteActualizado.getNombre()).isEqualTo(nuevoNombre);
}

@Test
public void ClienteRepository_findByEmail_ClienteEncontrado() {
    // 1. Arrange
    String email = "sofia@mail.com";
    Cliente cliente = new Cliente("Sofia", "Martinez", email, "password123", "3001234567", "Cra 7 #45");
    clienteRepository.save(cliente);

    // 2. Act
    Cliente encontrado = clienteRepository.findByEmail(email);

    // 3. Assert
    Assertions.assertThat(encontrado).isNotNull();
    Assertions.assertThat(encontrado.getEmail()).isEqualTo(email);
}


@Test
public void ClienteRepository_findByNombreAndApellido_Cliente() {
    Cliente cliente = new Cliente("Carla", "Lopez", "carla@gmail.com", "clave123", "111222333", "Javeriana");
    clienteRepository.save(cliente);

    Cliente found = clienteRepository.findByNombreAndApellido("Carla", "Lopez");
    Assertions.assertThat(found).isNotNull();
}

@Test
public void ClienteRepository_findByDireccionContaining_ClientesEncontrados() {
    Cliente c1 = new Cliente("Laura", "Diaz", "laura@mail.com", "password123", "1234567890", "Calle 45");
    Cliente c2 = new Cliente("Carlos", "Ramos", "carlos@mail.com", "password123", "0987654321", "Calle 46");
    clienteRepository.save(c1);
    clienteRepository.save(c2);

    List<Cliente> resultados = clienteRepository.findByDireccionContainingIgnoreCase("calle");

    Assertions.assertThat(resultados).isNotEmpty();
    Assertions.assertThat(resultados.size()).isEqualTo(2);
}

@Test
public void ClienteRepository_findByTelefono_ClienteEncontrado() {
    String telefono = "3001234567";
    Cliente cliente = new Cliente("Sofia", "Martinez", "sofia@mail.com", "password123", telefono, "Cra 7 #45");
    clienteRepository.save(cliente);

    Cliente encontrado = clienteRepository.findByTelefono(telefono);

    Assertions.assertThat(encontrado).isNotNull();
    Assertions.assertThat(encontrado.getTelefono()).isEqualTo(telefono);
}


    
}
