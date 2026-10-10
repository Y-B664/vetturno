package com.veterinaria.vetturno.dto;

import com.veterinaria.vetturno.model.Cita;
import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Veterinario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CitaDTO {

    private Long id;
    private String fechaHora;
    private String motivo;
    private String mascota;
    private String veterinario;

    public CitaDTO(Cita cita) {
        this.id = cita.getId();
        this.fechaHora = cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.motivo = cita.getMotivo();
        this.mascota = cita.getMascota() !=null? cita.getMascota().getNombre() + " - " + cita.getMascota().getRaza() : null;
        this.veterinario = cita.getVeterinario() != null ?  cita.getVeterinario().getNombre() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }
}
