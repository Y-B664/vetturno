package com.veterinaria.vetturno.service;

import com.veterinaria.vetturno.model.Propietario;
import com.veterinaria.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropietarioService {

    private PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public Propietario crearPropietario(Propietario propietario){
        return propietarioRepository.save(propietario);
    }
    public List<Propietario> listarPropietarios(){
        return propietarioRepository.findAll();
    }
}
