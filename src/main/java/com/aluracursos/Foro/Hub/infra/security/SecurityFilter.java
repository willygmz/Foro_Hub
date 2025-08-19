package com.aluracursos.Foro.Hub.infra.security;

import com.aluracursos.Foro.Hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println("Processing request to: " + requestURI);
        
        try {
            var tokenJWT = recuperarToken(request);
            System.out.println("Token from request: " + (tokenJWT != null ? "[TOKEN_PRESENT]" : "null"));
            
            if (tokenJWT != null) {
                System.out.println("Attempting to validate token...");
                var subject = tokenService.getSubject(tokenJWT);
                System.out.println("Token subject: " + subject);
                
                if (subject == null || subject.isBlank()) {
                    System.out.println("JWT is null.");
                } else {
                    var usuario = usuarioRepository.findByLogin(subject);
                    
                    if (usuario != null) {
                        System.out.println("Usuario no encontrado: " + usuario.getUsername() + " con roles: " + usuario.getAuthorities());
                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Usuario autenticado con Exito");
                    } else {
                        System.out.println("Ningun usuario fue encontrado con login : " + subject);
                    }
                }
            } else {
                System.out.println("Ningun JWT token encontrado en la peticion");
            }
            
            filterChain.doFilter(request, response);
            
        } catch (Exception e) {
            System.err.println("Error en SecurityFilter: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Authentication error: " + e.getMessage());
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ","");
        }
        return null;
    }

}
