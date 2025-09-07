package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.LoginResponse;
import com.fqstudio.attendance_system.application.academico.dto.RefreshTokenRequest;
import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.domain.academico.repository.UsuarioRepository;
import com.fqstudio.attendance_system.shared.security.JwtService;
import com.fqstudio.attendance_system.shared.security.UsuarioUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Implementación del caso de uso de refresh token
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {
    
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioUserDetailsService userDetailsService;
    
    @Override
    public LoginResponse execute(RefreshTokenRequest request) {
        log.info("Iniciando proceso de refresh token");
        
        // Extraer email del token
        String email = jwtService.extractUsername(request.getToken());
        
        // Buscar usuario
        Usuario usuario = usuarioRepository.findByCorreoAndActivoTrue(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado o inactivo"));
        
        // Obtener UserDetails para verificar token
        var userDetails = userDetailsService.loadUserByUsername(usuario.getCorreo());
        
        // Verificar si el token es válido
        if (!jwtService.isTokenValid(request.getToken(), userDetails)) {
            throw new RuntimeException("Token inválido");
        }
        
        // Generar nuevo token
        String newToken = jwtService.generateToken(userDetails);
        
        // Construir response
        LoginResponse response = LoginResponse.builder()
                .token(newToken)
                .tipo("Bearer")
                .id(usuario.getId())
                .email(usuario.getCorreo())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .roles(usuario.getRoles().stream()
                        .map(rol -> rol.getNombre())
                        .collect(Collectors.toSet()))
                .build();
        
        log.info("Refresh token exitoso para usuario: {}", email);
        return response;
    }
}
