package com.veterinaria.vetturno.service;

import com.veterinaria.vetturno.dto.AuthResponse;
import com.veterinaria.vetturno.dto.LoginRequest;
import com.veterinaria.vetturno.dto.RegistroRequest;
import com.veterinaria.vetturno.model.Rol;
import com.veterinaria.vetturno.model.Usuario;
import com.veterinaria.vetturno.repository.UsuarioRepository;
import com.veterinaria.vetturno.security.JwtService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse registrarUsuario(RegistroRequest registroRequest){
        Usuario usuario = new Usuario();
        usuario.setEmail(registroRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        usuario.setRol(Rol.USER);
        usuarioRepository.save(usuario);
        return new AuthResponse(jwtService.generarToken(usuario));
    }
    public AuthResponse loguearUsuario(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));
        Usuario usuario = usuarioRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow();

        return new AuthResponse(jwtService.generarToken(usuario));

    }

}
