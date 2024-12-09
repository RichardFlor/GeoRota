package com.georota.georota.maps.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Local extends Ponto {
    double distancia;
    @Getter
    private List<Ponto> elo;

    public Local(String nomePonto, String logradouro) {
        super(nomePonto, logradouro);
        this.elo = new ArrayList<>();
    }

    public Local(String nomePonto) {
        super(nomePonto);
    }

    public void criarEloLocal(Ponto ponto) {
        elo.add(ponto);
    }
}