package com.fqstudio.attendance_system.shared.security;

import com.fqstudio.attendance_system.domain.academico.model.Usuario;
import com.fqstudio.attendance_system.domain.academico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Servicio personalizado de UserDetails para Spring Security
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioUserDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Cargando usuario por email: {}", email);
        
        Usuario usuario = usuarioRepository.findByCorreoAndActivoTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        
        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .authorities(usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                        .collect(Collectors.toList()))
                .build();
    }
}
