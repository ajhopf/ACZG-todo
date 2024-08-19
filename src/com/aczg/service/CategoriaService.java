package com.aczg.service;

import com.aczg.model.Categoria;
import com.aczg.repository.CategoriaRepository;
import com.aczg.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaService {
    public static Categoria criarCategoria(String nome) {
        Categoria categoria = new Categoria(nome);
        categoria.setId(gerarIdParaNovaCategoria());

        CategoriaRepository.adicionarCategoria(categoria);
        System.out.println("Nova categoria criada: " + categoria);

        return categoria;
    }

    public static int gerarIdParaNovaCategoria() {
        List<Categoria> categorias = CategoriaRepository.getCategorias();
        return MyUtils.gerarNovoId(categorias);
    }

    public static boolean categoriaExiste(String nomeDaCategoria) {
        List<Categoria> categorias = CategoriaRepository.getCategorias();
        Categoria categoria = new Categoria(nomeDaCategoria);
        return categorias.contains(categoria);
    }

    public static Categoria buscarCategoriaPeloNome(String nome) {
        List<Categoria> categorias = CategoriaRepository.getCategorias();
        for (Categoria categoria : categorias) {
            if (Objects.equals(categoria.getCategoria(), nome)) {
                return categoria;
            }
        }
        return null;
    }

    public static List<Categoria> listarCategorias() {
        List<Categoria> categorias = CategoriaRepository.getCategorias();
        return categorias;
    }
}
