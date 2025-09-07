package com.fqstudio.attendance_system.domain.academico.service;

import com.fqstudio.attendance_system.domain.academico.model.Usuario;

/**
 * Servicio de dominio para autenticación
 */
public interface AutenticacionService {
    
    /**
     * Autentica un usuario con email y contraseña
     */
    Usuario autenticar(String email, String password);
    
    /**
     * Verifica si las credenciales son válidas
     */
    boolean verificarCredenciales(String email, String password);
    
    /**
     * Busca un usuario activo por email
     */
    Usuario buscarUsuarioActivo(String email);
}
