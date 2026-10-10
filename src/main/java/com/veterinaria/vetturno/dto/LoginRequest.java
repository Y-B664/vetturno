package com.veterinaria.vetturno.dto;

import jakarta.validation.constraints.Email;

public class LoginRequest {

    @Email(message = "El email no es valido")
    private String email;
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
