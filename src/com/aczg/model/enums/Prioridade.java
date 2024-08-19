package com.aczg.model.enums;

public enum Prioridade {
    MUITO_BAIXA(1, "Muito Baixa") ,
    BAIXA(2, "Baixa") ,
    MEDIA(3, "Média") ,
    ALTA(4, "Alta"),
    MUITO_ALTA(5, "Muito Alta"),;

    private final int valor;
    private final String descricao;

    Prioridade(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    @Override public String toString() {
        return descricao;
    }
}
