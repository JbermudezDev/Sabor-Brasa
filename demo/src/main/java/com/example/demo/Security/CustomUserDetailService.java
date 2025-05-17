package com.example.demo.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Role;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Administrador;
import com.example.demo.entidades.Operador;
import com.example.demo.entidades.Domiciliario;
import com.example.demo.entidades.UserEntity;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
           () -> new UsernameNotFoundException("User not found")
       );
       UserDetails userDetails = new User(userDB.getUsername(),
        userDB.getPassword(),
         mapRolesToAuthorities(userDB.getRoles()));

       return userDetails;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    

     public UserEntity saveUserAdmin(Administrador admin){
        UserEntity user = new UserEntity();
        user.setUsername(admin.getEmail());
        user.setPassword(passwordEncoder.encode(admin.getPassword()));
        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(List.of(roles));
        return userRepository.save(user);
    }

     public UserEntity saveUserCliente(Cliente cliente){
        UserEntity user = new UserEntity();
        user.setUsername(cliente.getEmail());
        user.setPassword(passwordEncoder.encode("123456"));
        Role roles = roleRepository.findByName("CLIENTE").get();
        user.setRoles(List.of(roles));
        return userRepository.save(user);
    }

        public UserEntity saveUserDomiciliario(Domiciliario domiciliario){
            UserEntity user = new UserEntity();
            user.setUsername(domiciliario.getCelular());
            user.setPassword(passwordEncoder.encode("123456"));
            Role roles = roleRepository.findByName("DOMICILIARIO").get();
            user.setRoles(List.of(roles));
            return userRepository.save(user);
        }
    
        public UserEntity saveUserOperador(Operador operador){
            UserEntity user = new UserEntity();
            user.setUsername(operador.getUsuario());
            user.setPassword(passwordEncoder.encode(operador.getContrasena()));
            Role roles = roleRepository.findByName("OPERADOR").get();
            user.setRoles(List.of(roles));
            return userRepository.save(user);
    }
        
    
}