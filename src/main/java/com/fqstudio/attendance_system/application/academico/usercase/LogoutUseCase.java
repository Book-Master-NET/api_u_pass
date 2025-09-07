package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.LogoutRequest;

/**
 * Caso de uso para logout de usuario
 */
public interface LogoutUseCase {
    
    /**
     * Ejecuta el logout del usuario
     */
    void execute(LogoutRequest request);
}
