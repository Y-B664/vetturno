package com.veterinaria.vetturno.dto;

import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Veterinario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class CitaRequest {


    private String motivo;
    @Positive(message = "El id no puede ser negativo o cero")
    @NotNull(message = "El campo del id de la mascota no puede estar vacio")
    private Long idMascota;
    @Positive(message = "El id no puede ser negativo o cero")
    @NotNull(message = "El campo del id del veterinario no puede estar vacio")
    private Long idVeterinario;
    @NotNull(message = "La fecha no puede estar vacia")
    @Future(message = "La fecha no puede ser anterior")
    private LocalDateTime fechaHora;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
