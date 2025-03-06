
package com.example.demo.entidades;
import lombok.Data;

@Data
public class LoginRequest {
    private String cedula;
    private String password;

    public LoginRequest(String cedula, String password) {
        this.cedula = cedula;
        this.password = password;
    }
    
   

    
}