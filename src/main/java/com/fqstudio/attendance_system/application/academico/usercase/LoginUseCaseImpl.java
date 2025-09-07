package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.LoginRequest;
import com.fqstudio.attendance_system.application.academico.dto.LoginResponse;
import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.domain.academico.service.AutenticacionService;
import com.fqstudio.attendance_system.shared.security.JwtService;
import com.fqstudio.attendance_system.shared.security.UsuarioUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Implementación del caso de uso de login
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUseCaseImpl implements LoginUseCase {
    
    private final AutenticacionService autenticacionService;
    private final JwtService jwtService;
    private final UsuarioUserDetailsService userDetailsService;
    
    @Override
    public LoginResponse execute(LoginRequest request) {
        log.info("Iniciando proceso de login para usuario: {}", request.getEmail());
        
        // Autenticar usuario
        Usuario usuario = autenticacionService.autenticar(request.getEmail(), request.getPassword());
        
        // Obtener UserDetails para generar token
        var userDetails = userDetailsService.loadUserByUsername(usuario.getCorreo());
        
        // Generar token JWT
        String token = jwtService.generateToken(userDetails);
        
        // Construir response
        LoginResponse response = LoginResponse.builder()
                .token(token)
                .tipo("Bearer")
                .id(usuario.getId())
                .email(usuario.getCorreo())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .roles(usuario.getRoles().stream()
                        .map(rol -> rol.getNombre())
                        .collect(Collectors.toSet()))
                .build();
        
        log.info("Login exitoso para usuario: {}", request.getEmail());
        return response;
    }
}
