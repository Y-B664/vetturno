package com.veterinaria.vetturno.controller;

import com.veterinaria.vetturno.dto.VeterinarioDTO;
import com.veterinaria.vetturno.dto.VeterinarioRequest;
import com.veterinaria.vetturno.model.Veterinario;
import com.veterinaria.vetturno.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/veterinarios")
public class VeterinarioController {
    private VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }
    @GetMapping
    public List<VeterinarioDTO> obtenerVeterinarios(){
        return veterinarioService.listarVeterinarios()
                .stream()
                .map(VeterinarioDTO::new)
                .toList();
    }
    @PostMapping
    public ResponseEntity<VeterinarioDTO> crearVeterinario(@Valid @RequestBody VeterinarioRequest veterinarioRequest){
        Veterinario veterinario = new Veterinario();
        veterinario.setEspecialidad(veterinarioRequest.getEspecialidad());
        veterinario.setNombre(veterinarioRequest.getNombre());
        Veterinario veterinarioGuardado = veterinarioService.crearVeterinario(veterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VeterinarioDTO(veterinarioGuardado));

    }
}
