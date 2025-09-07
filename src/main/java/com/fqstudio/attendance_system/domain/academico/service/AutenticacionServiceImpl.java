package com.fqstudio.attendance_system.domain.academico.service;

import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.domain.academico.repository.UsuarioRepository;
import com.fqstudio.attendance_system.shared.common.exception.UsuarioNoEncontradoException;
import com.fqstudio.attendance_system.shared.common.exception.CredencialesInvalidasException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de autenticación
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AutenticacionServiceImpl implements AutenticacionService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Usuario autenticar(String email, String password) {
        log.debug("Intentando autenticar usuario con email: {}", email);
        
        Usuario usuario = buscarUsuarioActivo(email);
        
        if (!verificarPassword(password, usuario.getPassword())) {
            log.warn("Credenciales inválidas para usuario: {}", email);
            throw new CredencialesInvalidasException("Credenciales inválidas");
        }
        
        log.info("Usuario autenticado exitosamente: {}", email);
        return usuario;
    }
    
    @Override
    public boolean verificarCredenciales(String email, String password) {
        try {
            autenticar(email, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public Usuario buscarUsuarioActivo(String email) {
        return usuarioRepository.findByCorreoAndActivoTrue(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado o inactivo: " + email));
    }
    
    /**
     * Verifica si la contraseña coincide
     */
    private boolean verificarPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
