package com.fqstudio.attendance_system.shared.common.exception;

/**
 * Excepción lanzada cuando las credenciales son inválidas
 */
public class CredencialesInvalidasException extends RuntimeException {
    
    public CredencialesInvalidasException(String message) {
        super(message);
    }
    
    public CredencialesInvalidasException(String message, Throwable cause) {
        super(message, cause);
    }
}
