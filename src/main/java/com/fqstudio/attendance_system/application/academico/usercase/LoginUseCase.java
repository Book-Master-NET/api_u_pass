package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.LoginRequest;
import com.fqstudio.attendance_system.application.academico.dto.LoginResponse;

/**
 * Caso de uso para login de usuario
 */
public interface LoginUseCase {
    
    /**
     * Ejecuta el login del usuario
     */
    LoginResponse execute(LoginRequest request);
}
