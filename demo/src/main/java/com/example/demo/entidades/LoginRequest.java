package com.example.demo.entidades;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Size(max = 20)
    private String cedula;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    public LoginRequest(String cedula, String password) {
        this.cedula = cedula;
        this.password = password;
    }
}