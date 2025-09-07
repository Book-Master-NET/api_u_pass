package com.fqstudio.attendance_system.application.academico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO para response de login
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    
    private String token;
    private String tipo;
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private Set<String> roles;
    
    public LoginResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
