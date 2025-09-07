package com.fqstudio.attendance_system.infrastructure.persistence.jpa.repository;

import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.domain.academico.repository.UsuarioRepository;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.UsuarioEntity;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementación del repositorio de dominio UsuarioRepository
 */
@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {
    
    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioMapper mapper;
    
    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return jpaRepository.findByCorreo(correo)
                .map(mapper::toDomain);
    }
    
    @Override
    public boolean existsByCorreo(String email) {
        return jpaRepository.existsByCorreo(email);
    }
    
    @Override
    public Optional<Usuario> findByCorreoAndActivoTrue(String email) {
        return jpaRepository.findByCorreoAndActivoTrue(email)
                .map(mapper::toDomain);
    }
}
