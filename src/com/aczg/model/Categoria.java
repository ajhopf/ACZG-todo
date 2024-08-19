package com.aczg.model;

public class Categoria {
    int id;
    String categoria;

    public Categoria(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

