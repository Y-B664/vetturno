package com.veterinaria.vetturno.service;


import com.veterinaria.vetturno.model.Veterinario;
import com.veterinaria.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    private VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public Veterinario crearVeterinario(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }
    public List<Veterinario> listarVeterinarios(){
        return veterinarioRepository.findAll();
    }
}
