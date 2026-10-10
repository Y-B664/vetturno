package com.veterinaria.vetturno.dto;

import jakarta.validation.constraints.NotNull;

public class VeterinarioRequest {
    @NotNull(message = "El campo nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "El campo especialidad no puede estar vacio")
    private String especilidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especilidad;
    }

    public void setEspecialidad(String especilidad) {
        this.especilidad = especilidad;
    }
}
