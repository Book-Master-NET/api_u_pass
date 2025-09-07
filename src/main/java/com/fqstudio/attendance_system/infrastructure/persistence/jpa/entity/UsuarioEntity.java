package com.fqstudio.attendance_system.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Entidad JPA para Usuario
 */
@Entity
@Table(name = "usuarios", 
       uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String carnet;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;

    @CreationTimestamp
    @Column(name = "fecha_vencimiento_carnet", nullable = false, updatable = true)
    private Date fechaVencimientoCarnet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<RolEntity> roles;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
    
    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @UpdateTimestamp
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}
