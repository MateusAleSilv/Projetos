package com.mktech.mktechlog.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioModel {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}
