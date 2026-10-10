package com.veterinaria.vetturno.controller;

import com.veterinaria.vetturno.dto.PropietarioDTO;
import com.veterinaria.vetturno.dto.PropietarioRequest;
import com.veterinaria.vetturno.model.Propietario;
import com.veterinaria.vetturno.service.PropietarioService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/propietarios")
public class PropietarioController {

    private PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }
    @GetMapping
    public List<PropietarioDTO> obtenerPropietarios(){
        return propietarioService.listarPropietarios()
                .stream()
                .map(PropietarioDTO::new)
                .toList();
    }
    @PostMapping
    public ResponseEntity<PropietarioDTO> crearPropietario(@Valid @RequestBody PropietarioRequest propietarioRequest){
        Propietario propietario = new Propietario();
        propietario.setNombre(propietarioRequest.getNombre());
        propietario.setEmail(propietarioRequest.getEmail());
        propietario.setTelefono(propietarioRequest.getTelefono());
        Propietario propietarioGuardado = propietarioService.crearPropietario(propietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PropietarioDTO(propietarioGuardado));
    }

}
