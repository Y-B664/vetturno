package com.veterinaria.vetturno.dto;

import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Propietario;
import jakarta.persistence.*;

public class MascotaDTO {

    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String propietario;

    public MascotaDTO(Mascota mascota) {
        this.id = mascota.getId();
        this.nombre = mascota.getNombre();
        this.especie = mascota.getEspecie();
        this.raza = mascota.getRaza();
        this.propietario = mascota.getPropietario() != null
                ? mascota.getPropietario().getNombre() + " email: " + mascota.getPropietario().getEmail()
                : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
