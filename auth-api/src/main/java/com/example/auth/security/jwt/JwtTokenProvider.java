package com.example.auth.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value(value = "${security.jwt.token.secret-key}")
    private String secretKey;

    @Value(value = "${security.jwt.token.expire-length}")
    private Long expire;

    @Qualifier("usuarioService")
    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userName, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("roles",roles);

        Date now = new Date();
        Date validate = new Date(now.getTime() + expire);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
            return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    private String getUserName(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
    public boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        }catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }

}
