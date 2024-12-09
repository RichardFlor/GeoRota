package com.georota.georota.maps.entities;

import lombok.Getter;

@Getter
public abstract class Rua {
    String logradouro;

    public Rua() {}

    public Rua(String logradouro) {
        this.logradouro = logradouro;
    }
}
