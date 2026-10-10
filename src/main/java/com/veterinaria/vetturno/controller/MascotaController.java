package com.veterinaria.vetturno.controller;

import com.veterinaria.vetturno.dto.MascotaDTO;
import com.veterinaria.vetturno.dto.MascotaRequest;
import com.veterinaria.vetturno.model.Mascota;
import com.veterinaria.vetturno.model.Propietario;
import com.veterinaria.vetturno.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {
    private MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }
    @GetMapping
    public List<MascotaDTO> obtenerMascotas(){
        return mascotaService.listarMascotas()
                .stream()
                .map(MascotaDTO::new)
                .toList();
    }
    @GetMapping("propietario/{idPropietario}")
    public List<MascotaDTO> obtenerMascotasPorPropietario(@PathVariable Long idPropietario){
        return mascotaService.listarMascotasPorPropietario(idPropietario)
                .stream()
                .map(MascotaDTO::new)
                .toList();
    }
    @PostMapping
    public ResponseEntity<MascotaDTO> crearMascota(@Valid @RequestBody MascotaRequest mascotaRequest){

        Mascota mascota = new Mascota();
        mascota.setNombre(mascotaRequest.getNombre());
        mascota.setEspecie(mascotaRequest.getEspecie());
        mascota.setRaza(mascotaRequest.getRaza());
        Propietario propietario = new Propietario();
        propietario.setId(mascotaRequest.getPropietarioId());
        mascota.setPropietario(propietario);
        Mascota mascotaGuardada = mascotaService.crearMascota(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MascotaDTO(mascotaGuardada));
    }
}
