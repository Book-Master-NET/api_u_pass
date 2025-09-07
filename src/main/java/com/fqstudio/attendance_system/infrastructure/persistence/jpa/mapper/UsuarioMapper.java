package com.fqstudio.attendance_system.infrastructure.persistence.jpa.mapper;

import com.fqstudio.attendance_system.domain.academico.model.Rol;
import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.RolEntity;
import com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper MapStruct para convertir entre Usuario y UsuarioEntity
 */
@Mapper(componentModel = "spring", uses = RolMapper.class)
public interface UsuarioMapper {
    
    /**
     * Convierte UsuarioEntity a Usuario
     */
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    Usuario toDomain(UsuarioEntity entity);
    
    /**
     * Convierte Usuario a UsuarioEntity
     */
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToEntity")
    UsuarioEntity toEntity(Usuario domain);
    
    /**
     * Mapea los roles de entidad a dominio
     */
    @Named("mapRoles")
    default Set<Rol> mapRoles(
            Set<RolEntity> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(rol -> com.fqstudio.attendance_system.domain.academico.model.Rol.builder()
                        .id(rol.getId())
                        .nombre(rol.getNombre())
                        .descripcion(rol.getDescripcion())
                        .fechaCreacion(rol.getFechaCreacion())
                        .ultimaActualizacion(rol.getUltimaActualizacion())
                        .build())
                .collect(Collectors.toSet());
    }
    
    /**
     * Mapea los roles de dominio a entidad
     */
    @Named("mapRolesToEntity")
    default Set<RolEntity> mapRolesToEntity(
            Set<Rol> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(rol -> com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity.RolEntity.builder()
                        .id(rol.getId())
                        .nombre(rol.getNombre())
                        .descripcion(rol.getDescripcion())
                        .fechaCreacion(rol.getFechaCreacion())
                        .ultimaActualizacion(rol.getUltimaActualizacion())
                        .build())
                .collect(Collectors.toSet());
    }
}
