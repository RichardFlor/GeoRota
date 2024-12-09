package com.georota.georota.maps.entities;

import lombok.Getter;

@Getter
public abstract class Ponto extends Rua {
    private final String nomePonto;

    public Ponto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public Ponto(String nomePonto, String logradouro) {
        super(logradouro);
        this.nomePonto = nomePonto;
    }
}
