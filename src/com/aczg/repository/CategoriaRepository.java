package com.aczg.repository;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {
    static private List<Categoria> categorias = new ArrayList<>();
    static public void adicionarCategoria(Categoria categoria) {categorias.add(categoria);}
    static public void removerCategoria(Categoria categoria) {categorias.remove(categoria);}
    static public List<Categoria> getCategorias() {return categorias;}
}
