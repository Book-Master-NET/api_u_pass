package com.fqstudio.attendance_system.infrastructure.persistence.jpa.repository;

import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para RolEntity
 */
@Repository
public interface RolJpaRepository extends JpaRepository<RolEntity, Long> {
    
    /**
     * Busca un rol por nombre
     */
    Optional<RolEntity> findByNombre(String nombre);
    
    /**
     * Verifica si existe un rol con el nombre dado
     */
    boolean existsByNombre(String nombre);
}
