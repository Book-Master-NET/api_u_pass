package com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad JPA para Rol
 */
@Entity
@Table(name = "roles", 
       uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    private String descripcion;
    
    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @UpdateTimestamp
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}
