package com.fqstudio.attendance_system.infrastructure.persistence.jpa.mapper;

import com.fqstudio.attendance_system.domain.academico.model.Rol;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.RolEntity;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct para convertir entre Rol y RolEntity
 */
@Mapper(componentModel = "spring")
public interface RolMapper {
    
    /**
     * Convierte RolEntity a Rol
     */
    Rol toDomain(RolEntity entity);
    
    /**
     * Convierte Rol a RolEntity
     */
    RolEntity toEntity(Rol domain);
}
