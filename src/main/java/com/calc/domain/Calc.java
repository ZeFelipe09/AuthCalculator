package com.calc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "calculo")
public class Calc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty("expressao")
    @Column(name = "expressao")
    private String expression;

    @JsonProperty("resultado")
    @Column(name = "resultado")
    private Float result;


}
