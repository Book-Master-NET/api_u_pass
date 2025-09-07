package com.fqstudio.attendance_system.application.academico.usercase;

import com.fqstudio.attendance_system.application.academico.dto.RefreshTokenRequest;
import com.fqstudio.attendance_system.application.academico.dto.LoginResponse;

/**
 * Caso de uso para refresh token
 */
public interface RefreshTokenUseCase {
    
    /**
     * Ejecuta el refresh del token
     */
    LoginResponse execute(RefreshTokenRequest request);
}
