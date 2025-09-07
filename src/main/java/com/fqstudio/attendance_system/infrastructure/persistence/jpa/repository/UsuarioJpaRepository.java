package com.fqstudio.attendance_system.infrastructure.persistence.jpa.repository;

import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para UsuarioEntity
 */
@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    
    /**
     * Busca un usuario por email
     */
    Optional<UsuarioEntity> findByCorreo(String correo);
    
    /**
     * Verifica si existe un usuario con el email dado
     */
    boolean existsByCorreo(String correo);
    
    /**
     * Busca usuarios activos por email
     */
    Optional<UsuarioEntity> findByCorreoAndActivoTrue(String correo);
}
