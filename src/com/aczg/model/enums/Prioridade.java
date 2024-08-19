package com.aczg.model.enums;

public enum Prioridade {
    MUITO_BAIXA(1),
    BAIXA(2),
    MEDIA(3),
    ALTA(4),
    MUITO_ALTA(5);

    private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
