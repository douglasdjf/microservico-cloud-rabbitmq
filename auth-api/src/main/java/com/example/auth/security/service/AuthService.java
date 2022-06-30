package com.example.auth.security.service;

import com.example.auth.domain.repository.UsuarioRepository;
import com.example.auth.dto.UsuarioDTO;
import com.example.auth.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    public  Map<Object,Object> login(UsuarioDTO usuarioDTO){

        try{
            var userName = usuarioDTO.getUserName();
            var password = usuarioDTO.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

            var user = usuarioRepository.findByUserName(userName);
            var token = "";

            if(user.isPresent()) {
                token = jwtTokenProvider.createToken(userName, user.get().getRoles());
            }else{
                throw new UsernameNotFoundException("Usuario não encontrado");
            }

            Map<Object,Object> model = new HashMap<>();
            model.put("username",userName);
            model.put("token",token);
            return model;

        }catch (AuthenticationException ex){
            throw new BadCredentialsException("Usuario/Senha Inválido");
        }
    }
}
