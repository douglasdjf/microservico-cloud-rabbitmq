package com.example.auth.security.config;

import com.example.auth.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
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
                .antMatchers("/login/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/h2/**")
                .and().headers().frameOptions().sameOrigin()

          .and()
          .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
