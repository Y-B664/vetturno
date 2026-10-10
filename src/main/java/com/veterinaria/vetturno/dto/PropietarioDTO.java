package com.veterinaria.vetturno.dto;

import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Propietario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class PropietarioDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private List<String> mascotas =  new ArrayList<>();

    public PropietarioDTO(Propietario propietario) {
        this.id = propietario.getId();
        this.nombre = propietario.getNombre();
        this.telefono = propietario.getTelefono();
        this.email = propietario.getEmail();
        if(propietario.getMascotas() != null){
            for (Mascota mascota : propietario.getMascotas()){
                this.mascotas.add(mascota.getNombre() + " - " + mascota.getRaza());
            }
        }
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

    public List<String> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<String> mascotas) {
        this.mascotas = mascotas;
    }
}
