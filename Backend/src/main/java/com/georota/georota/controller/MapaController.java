package com.georota.georota.controller;

import com.georota.georota.maps.entities.Local;
import com.georota.georota.maps.services.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mapa")
@CrossOrigin
public class MapaController {

    private final Cidade cidade;

    @Autowired
    public MapaController(Cidade cidade) {
        this.cidade = cidade;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Map<String, String>> adicionarLocal(@RequestParam String nomePonto, @RequestParam String logradouro) {
        cidade.adicionarLocal(nomePonto, logradouro);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Local adicionado com sucesso.");
        response.put("Local", nomePonto);
        response.put("Rua", logradouro);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar-local")
    public ResponseEntity<Map<String, String>> buscarLocalArvore(@RequestParam String nomePonto) {
        Local local = cidade.buscarLocalArvore(nomePonto);
        if (local == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Map<String, String> response = new HashMap<>();
        response.put("nomePonto", local.getNomePonto());
        response.put("logradouro", local.getLogradouro());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/conectar-elos")
    public ResponseEntity<Map<String, String>> conectarElos(@RequestParam String nomeOrigem, @RequestParam String nomeDestino) {
        Map<String, String> response = new HashMap<>();
        if (!cidade.existePonto(nomeOrigem) && !cidade.existePonto(nomeDestino)) {
            response.put("erro", "Erro: Os pontos de origem e destino não existem.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!cidade.existePonto(nomeOrigem)) {
            response.put("erro", "Erro: O ponto de origem " + nomeOrigem + " não existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!cidade.existePonto(nomeDestino)) {
            response.put("erro", "Erro: O ponto de destino " + nomeDestino + " não existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            cidade.conectarElos(nomeOrigem, nomeDestino);
            response.put("mensagem", "Pontos conectados com sucesso entre " + nomeOrigem + " e " + nomeDestino);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/conexoes")
    public ResponseEntity<String> connections() {
        String connections = cidade.connections();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(connections);
    }

    @GetMapping("/melhor-rota")
    public ResponseEntity<String> obterMelhorRota(@RequestParam String nomeOrigem, @RequestParam String nomeDestino, @RequestParam String modo) {
        String rota = cidade.obterMelhorRotaEntrePontos(nomeOrigem, nomeDestino, modo);
        if (rota.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .body(rota);
        }
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(rota);
    }
}