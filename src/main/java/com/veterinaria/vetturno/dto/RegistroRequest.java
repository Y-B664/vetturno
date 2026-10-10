package com.veterinaria.vetturno.dto;

import jakarta.validation.constraints.*;

public class RegistroRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email ingresado no es valido")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8,message = "La contraseña debe ser minimo de 8 caracteres")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
