package com.example.auth.security.resource;

import com.example.auth.dto.UsuarioDTO;
import com.example.auth.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(authService.login(usuarioDTO));
    }

    @RequestMapping("/testeSecurity")
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("TESTE OK");
    }

}
