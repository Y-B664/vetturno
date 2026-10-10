package com.veterinaria.vetturno.service;

import com.veterinaria.vetturno.dto.MascotaDTO;
import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Propietario;
import com.veterinaria.vetturno.repository.MascotaRepository;
import com.veterinaria.vetturno.repository.PropietarioRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private MascotaRepository mascotaRepository;
    private PropietarioRepository propietarioRepository;

    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public Mascota crearMascota(Mascota mascota){
        agregarPropietario(mascota);
        return mascotaRepository.save(mascota);
    }
    public List<Mascota> listarMascotas(){
        return mascotaRepository.findAll();
    }
    public List<Mascota> listarMascotasPorPropietario(Long idPropietario){
        return mascotaRepository.findByPropietarioId(idPropietario);
    }

    private void agregarPropietario(Mascota mascota){
        if(mascota.getPropietario() != null && mascota.getPropietario().getId() != null){
            Propietario propietario = propietarioRepository
                    .findById(mascota.getPropietario().getId())
                    .orElseThrow();
            mascota.setPropietario(propietario);
        }
    }
}
