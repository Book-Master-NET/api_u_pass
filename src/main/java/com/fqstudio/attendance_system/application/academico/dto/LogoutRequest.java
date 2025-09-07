package com.fqstudio.attendance_system.application.academico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para request de logout
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest {
    
    @NotBlank(message = "El token es obligatorio")
    private String token;
}
