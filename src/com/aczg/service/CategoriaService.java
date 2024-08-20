package com.aczg.service;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.repository.CategoriaRepository;
import com.aczg.utils.MyUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoriaService {
    public static Categoria criarCategoria(String nome) {
        Categoria categoria = new Categoria(nome);
        categoria.setId(gerarIdParaNovaCategoria());

        CategoriaRepository.adicionarCategoria(categoria);
        System.out.println("----------------------------------------");
        System.out.println("Nova categoria criada: " + categoria.getCategoria());
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

    public static void removerCategoria(int id) {
        Optional<Categoria> categoria = CategoriaRepository
                .getCategorias()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (categoria.isPresent()) {
            List<Tarefa> tarefasComACategoria = TarefaService.filtrarTarefas(t -> t.getCategoria() == categoria.get());

            if (tarefasComACategoria.size() > 0) {
                System.out.println("Você possui tarefas com a categoria que você deseja deletar!");
                System.out.println("Antes de fazer isso, atualize suas tarefas com outras categorias.");
                System.out.println("Categoria " + categoria.get() + " não deletada.");
            } else {
                CategoriaRepository.removerCategoria(categoria.get());
                System.out.println("Categoria " + categoria.get() + " deletada.");
            }
        }
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
