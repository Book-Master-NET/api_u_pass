package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.LogoutRequest;
import com.fqstudio.attendance_system.shared.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementación del caso de uso de logout
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutUseCaseImpl implements LogoutUseCase {
    
    private final JwtService jwtService;
    
    @Override
    public void execute(LogoutRequest request) {
        log.info("Iniciando proceso de logout");
        
        // Extraer email del token
        String email = jwtService.extractUsername(request.getToken());
        
        // En un sistema más complejo, aquí podrías:
        // 1. Agregar el token a una blacklist
        // 2. Invalidar el token en la base de datos
        // 3. Limpiar sesiones activas
        
        // Por ahora, solo logueamos la acción
        log.info("Logout exitoso para usuario: {}", email);
    }
}
