package com.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
}
