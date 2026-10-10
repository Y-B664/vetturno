package com.veterinaria.vetturno.controller;

import com.veterinaria.vetturno.dto.CitaDTO;
import com.veterinaria.vetturno.dto.CitaRequest;
import com.veterinaria.vetturno.model.Cita;
import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Veterinario;
import com.veterinaria.vetturno.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/citas")
public class CitaController {
    private CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }
    @GetMapping
    public List<CitaDTO> obtenerAgenda(){
        return citaService.obtenerCitas()
                .stream()
                .map(CitaDTO::new)
                .toList();
    }
    @GetMapping("veterinario/{idVeterinario}")
    public List<CitaDTO> obtenerAgendaVeterinario(@PathVariable Long idVeterinario){
        return citaService.obtenerCitasVeterinario(idVeterinario)
                .stream()
                .map(CitaDTO::new)
                .toList();
    }
    @PostMapping
    public ResponseEntity<CitaDTO> agendarCita(@Valid @RequestBody CitaRequest citaRequest){
        Cita cita = new Cita();
        cita.setFechaHora(citaRequest.getFechaHora());
        cita.setMotivo(citaRequest.getMotivo());
        Mascota mascota = new Mascota();
        mascota.setId(citaRequest.getIdMascota());
        Veterinario veterinario = new Veterinario();
        veterinario.setId(citaRequest.getIdVeterinario());
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        Cita citaAgendada = citaService.crearCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CitaDTO(citaAgendada));
    }
}
