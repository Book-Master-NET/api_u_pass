package com.fqstudio.attendance_system.domain.academico.repository;

import com.fqstudio.attendance_system.domain.academico.model.Usuario;

import java.util.Optional;

/**
 * Repositorio de dominio para Usuario
 */
public interface UsuarioRepository {
    
    /**
     * Guarda un usuario
     */
    Usuario save(Usuario usuario);
    
    /**
     * Busca un usuario por ID
     */
    Optional<Usuario> findById(Long id);
    
    /**
     * Busca un usuario por correo
     */
    Optional<Usuario> findByCorreo(String correo);
    
    /**
     * Verifica si existe un usuario con el correo dado
     */
    boolean existsByCorreo(String correo);
    
    /**
     * Busca usuarios activos por correo
     */
    Optional<Usuario> findByCorreoAndActivoTrue(String correo);
}
