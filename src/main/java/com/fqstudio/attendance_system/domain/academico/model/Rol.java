package com.fqstudio.attendance_system.domain.academico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Modelo de dominio para Rol
 * Representa un rol de usuario en el sistema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaActualizacion;
    
    /**
     * Roles predefinidos del sistema
     */
    public static class Nombres {
        public static final String ADMIN = "ADMIN";
        public static final String PROFESOR = "PROFESOR";
        public static final String ESTUDIANTE = "ESTUDIANTE";
        public static final String PERSONAL = "PERSONAL";
    }
}
