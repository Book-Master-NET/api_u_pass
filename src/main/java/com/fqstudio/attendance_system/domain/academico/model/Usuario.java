package com.fqstudio.attendance_system.domain.academico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Modelo de dominio para Usuario
 * Representa un usuario del sistema de asistencia
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    private Long id;
    private String correo;
    private String carnet;
    private String password;
    private String nombre;
    private String apellido;
    private Set<Rol> roles;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaActualizacion;
    
    /**
     * Verifica si el usuario tiene un rol específico
     */
    public boolean tieneRol(String nombreRol) {
        return roles != null && roles.stream()
                .anyMatch(rol -> rol.getNombre().equals(nombreRol));
    }
    
    /**
     * Verifica si el usuario está activo
     */
    public boolean estaActivo() {
        return activo;
    }
}
