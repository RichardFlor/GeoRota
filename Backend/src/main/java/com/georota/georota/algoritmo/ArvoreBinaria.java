package com.georota.georota.algoritmo;

import com.georota.georota.maps.entities.Local;

public class ArvoreBinaria {
    private Node raiz;

    public void addLocal(Local local) {
        raiz = addRecursivo(raiz, local);
    }

    private Node addRecursivo(Node raiz, Local local) {
        if (raiz == null) {
            return new Node(local);
        }
        if (local.getNomePonto().compareTo(raiz.local.getNomePonto()) < 0) {
            raiz.esquerda = addRecursivo(raiz.esquerda, local);
        } else if (local.getNomePonto().compareTo(raiz.local.getNomePonto()) > 0) {
            raiz.direita = addRecursivo(raiz.direita, local);
        }
        return raiz;
    }

    public Local buscar(String nomePonto) {
        return buscarRecursivo(raiz, nomePonto);
    }

    private Local buscarRecursivo(Node raiz, String nomePonto) {
        if (raiz == null || raiz.local.getNomePonto().equals(nomePonto)) {
            return raiz != null ? raiz.local : null;
        }
        if (nomePonto.compareTo(raiz.local.getNomePonto()) < 0) {
            return buscarRecursivo(raiz.esquerda, nomePonto);
        }
        return buscarRecursivo(raiz.direita, nomePonto);
    }

    public static class Node {
        Local local;
        Node esquerda, direita;

        public Node(Local local) {
            this.local = local;
            this.esquerda = null;
            this.direita = null;
        }
    }
}