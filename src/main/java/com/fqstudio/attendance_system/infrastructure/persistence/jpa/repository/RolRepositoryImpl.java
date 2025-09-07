package com.fqstudio.attendance_system.infrastructure.persistence.jpa.repository;

import com.fqstudio.attendance_system.domain.academico.model.Rol;
import com.fqstudio.attendance_system.domain.academico.repository.RolRepository;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.RolEntity;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.mapper.RolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementación del repositorio de dominio RolRepository
 */
@Repository
@RequiredArgsConstructor
public class RolRepositoryImpl implements RolRepository {
    
    private final RolJpaRepository jpaRepository;
    private final RolMapper mapper;
    
    @Override
    public Rol save(Rol rol) {
        RolEntity entity = mapper.toEntity(rol);
        RolEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Rol> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre)
                .map(mapper::toDomain);
    }
    
    @Override
    public boolean existsByNombre(String nombre) {
        return jpaRepository.existsByNombre(nombre);
    }
}
