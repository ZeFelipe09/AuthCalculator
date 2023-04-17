package com.calc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Usuario")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "login")
    @JsonProperty("login")
    private String login;

    @Column(name = "senha")
    @JsonProperty("senha")
    private String password;

    @Column(name = "nome")
    @JsonProperty("nome")
    private String name;

    @Column(name = "administrador")
    @JsonProperty("admin")
    private Boolean isAdmin;
}
