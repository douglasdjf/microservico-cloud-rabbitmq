package com.example.auth.domain.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = {@JoinColumn(name = "usuario_id")}, inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissoes;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPermissoes();
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        this.permissoes.stream()
                .map(permissao ->
                        roles.add(permissao.getDescricao())
                );
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
