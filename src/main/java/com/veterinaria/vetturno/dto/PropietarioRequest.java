package com.veterinaria.vetturno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class PropietarioRequest {

    @NotNull(message = "El campo nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "El campo telefono no puede estar vacio")
    private String telefono;
    @NotNull(message = "El campo email no puede estar vacio")
    @Email(message = "El email ingresado no es valido")
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
