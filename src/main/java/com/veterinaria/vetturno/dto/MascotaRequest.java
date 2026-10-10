package com.veterinaria.vetturno.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class MascotaRequest {

    @NotNull(message = "El campo nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "El campo especie no puede estar vacio")
    private String especie;
    @NotNull(message = "El campo raza no puede estar vacio")
    private String raza;
    @Positive(message = "El id no puede ser negativo o cero")
    @NotNull(message = "El campo del id del propietario no puede estar vacio")
    private Long propietarioId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Long propietarioId) {
        this.propietarioId = propietarioId;
    }
}
