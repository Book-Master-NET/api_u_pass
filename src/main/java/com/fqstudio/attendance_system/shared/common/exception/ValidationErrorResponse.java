package com.fqstudio.attendance_system.shared.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO para respuestas de error de validación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorResponse extends ErrorResponse {
    
    private Map<String, String> validationErrors;
    
    @Builder(builderMethodName = "validationErrorBuilder")
    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, 
                                   String message, String path, Map<String, String> validationErrors) {
        super(timestamp, status, error, message, path);
        this.validationErrors = validationErrors;
    }
}
