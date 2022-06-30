package com.example.auth.security.config;

import com.example.auth.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtConfigurer jwtConfigurer;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()  throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .httpBasic().disable()
          .csrf().disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
             //   .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
             //   .and().csrf().ignoringAntMatchers("/h2/**")
             //   .and().headers().frameOptions().sameOrigin()
          .and()
          .apply(jwtConfigurer);
    }
}
