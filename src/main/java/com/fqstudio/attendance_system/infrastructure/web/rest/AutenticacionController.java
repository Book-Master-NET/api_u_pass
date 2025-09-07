package com.fqstudio.attendance_system.infrastructure.web.rest;

import com.fqstudio.attendance_system.application.academico.dto.LoginRequest;
import com.fqstudio.attendance_system.application.academico.dto.LoginResponse;
import com.fqstudio.attendance_system.application.academico.dto.LogoutRequest;
import com.fqstudio.attendance_system.application.academico.dto.RefreshTokenRequest;
import com.fqstudio.attendance_system.application.academico.usercase.LoginUseCase;
import com.fqstudio.attendance_system.application.academico.usercase.LogoutUseCase;
import com.fqstudio.attendance_system.application.academico.usercase.RefreshTokenUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para endpoints de autenticación
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Autenticación", description = "Endpoints para autenticación de usuarios")
public class AutenticacionController {
    
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y retorna un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Recibida petición de login para usuario: {}", request.getEmail());
        
        LoginResponse response = loginUseCase.execute(request);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/refresh")
    @Operation(summary = "Renovar token", description = "Renueva un token JWT válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token renovado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Token inválido"),
            @ApiResponse(responseCode = "401", description = "Token expirado o inválido")
    })
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        log.info("Recibida petición de refresh token");
        
        LoginResponse response = refreshTokenUseCase.execute(request);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    @Operation(summary = "Cerrar sesión", description = "Cierra la sesión del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout exitoso"),
            @ApiResponse(responseCode = "400", description = "Token inválido")
    })
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request) {
        log.info("Recibida petición de logout");
        
        logoutUseCase.execute(request);
        
        return ResponseEntity.ok().build();
    }
}
