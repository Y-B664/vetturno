package com.veterinaria.vetturno.controller;

import com.veterinaria.vetturno.dto.AuthResponse;
import com.veterinaria.vetturno.dto.LoginRequest;
import com.veterinaria.vetturno.dto.RegistroRequest;
import com.veterinaria.vetturno.model.Usuario;
import com.veterinaria.vetturno.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/autenticacion")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/registrar")
    public AuthResponse registrarUsuario(@Valid @RequestBody RegistroRequest registroRequest){
        return authService.registrarUsuario(registroRequest);
    }
    @PostMapping("/login")
    public AuthResponse loguearUsuario(@Valid @RequestBody LoginRequest loginRequest){
        return authService.loguearUsuario(loginRequest);
    }

}
