package com.fqstudio.attendance_system.infrastructure.config;

import com.fqstudio.attendance_system.domain.academico.model.Rol;
import com.fqstudio.attendance_system.domain.academico.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos básicos del sistema
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final RolRepository rolRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Inicializando datos básicos del sistema...");
        
        // Crear roles básicos si no existen
        crearRolSiNoExiste(Rol.Nombres.ADMIN, "Administrador del sistema");
        crearRolSiNoExiste(Rol.Nombres.PROFESOR, "Profesor");
        crearRolSiNoExiste(Rol.Nombres.ESTUDIANTE, "Estudiante");
        crearRolSiNoExiste(Rol.Nombres.PERSONAL, "Personal administrativo");
        
        log.info("Inicialización de datos completada");
    }
    
    private void crearRolSiNoExiste(String nombre, String descripcion) {
        if (!rolRepository.existsByNombre(nombre)) {
            Rol rol = Rol.builder()
                    .nombre(nombre)
                    .descripcion(descripcion)
                    .build();
            
            rolRepository.save(rol);
            log.info("Rol creado: {}", nombre);
        } else {
            log.debug("Rol ya existe: {}", nombre);
        }
    }
}
