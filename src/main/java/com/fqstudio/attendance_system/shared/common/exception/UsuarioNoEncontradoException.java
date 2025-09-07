package com.fqstudio.attendance_system.shared.common.exception;

/**
 * Excepción lanzada cuando no se encuentra un usuario
 */
public class UsuarioNoEncontradoException extends RuntimeException {
    
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
    
    public UsuarioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
