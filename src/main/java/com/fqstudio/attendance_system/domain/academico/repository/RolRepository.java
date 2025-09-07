package com.fqstudio.attendance_system.domain.academico.repository;

import com.fqstudio.attendance_system.domain.academico.model.Rol;

import java.util.Optional;

/**
 * Repositorio de dominio para Rol
 */
public interface RolRepository {
    
    /**
     * Guarda un rol
     */
    Rol save(Rol rol);
    
    /**
     * Busca un rol por ID
     */
    Optional<Rol> findById(Long id);
    
    /**
     * Busca un rol por nombre
     */
    Optional<Rol> findByNombre(String nombre);
    
    /**
     * Verifica si existe un rol con el nombre dado
     */
    boolean existsByNombre(String nombre);
}
