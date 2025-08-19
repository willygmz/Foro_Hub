package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.domain.usuario.DatosAutenticacion;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import com.aluracursos.Foro.Hub.infra.security.DatosTokenJWT;
import com.aluracursos.Foro.Hub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Authentication management endpoints")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
          return  ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
