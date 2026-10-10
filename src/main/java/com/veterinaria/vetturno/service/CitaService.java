package com.veterinaria.vetturno.service;

import com.veterinaria.vetturno.model.Cita;
import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Veterinario;
import com.veterinaria.vetturno.repository.CitaRepository;
import com.veterinaria.vetturno.repository.MascotaRepository;
import com.veterinaria.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {
    private CitaRepository citaRepository;
    private MascotaRepository mascotaRepository;
    private VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Cita> obtenerCitas(){
        return citaRepository.findAll();
    }
    public List<Cita> obtenerCitasVeterinario(Long idVeterinario){
        return citaRepository.findByVeterinarioId(idVeterinario);
    }
    public Cita crearCita(Cita cita){
        fechaHoraCorrecta(cita.getFechaHora());
        verificarAgendaVeterinario(cita);
        agregarMascota(cita);
        agregarVeterinario(cita);
        return citaRepository.save(cita);
    }

    private void agregarVeterinario(Cita cita){
        if(cita.getVeterinario() != null && cita.getVeterinario().getId() != null){
            Veterinario veterinario = veterinarioRepository.findById(cita.getVeterinario().getId()).orElseThrow(NullPointerException::new);
            cita.setVeterinario(veterinario);
        }
    }
    private void agregarMascota(Cita cita){
        if(cita.getMascota() != null && cita.getMascota().getId() != null){
            Mascota mascota = mascotaRepository.findById(cita.getMascota().getId()).orElseThrow(NullPointerException::new);
            cita.setMascota(mascota);
        }
    }
    private void fechaHoraCorrecta(LocalDateTime fechaHora){
        if(fechaHora.isBefore(LocalDateTime.now())){
            throw new RuntimeException("La fecha ya paso");
        }
    }
    private void verificarAgendaVeterinario(Cita cita){
        boolean veterinarioOcupado = citaRepository
                .existsByVeterinarioIdAndFechaHora(cita.getVeterinario().getId(),cita.getFechaHora());
        if(veterinarioOcupado) {
            throw new RuntimeException("El veterinario ya tiene una cita a la misma fecha y hora");
        }
    }


}
