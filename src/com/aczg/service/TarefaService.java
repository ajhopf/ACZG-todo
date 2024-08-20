package com.aczg.service;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.repository.TarefaRepository;
import com.aczg.utils.MyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class TarefaService {
    public static List<Tarefa> getTarefas() {
        List<Tarefa> tarefas = TarefaRepository.getTarefas();

        return tarefas;
    }
    public static Tarefa criarTarefa(String nome, String descricao, Date dataDeTermino, Prioridade prioridade, Categoria categoria, Status status) {
        Tarefa tarefa = new Tarefa(nome, descricao, dataDeTermino, prioridade, categoria, status);
        tarefa.setId(gerarIdParaNovaTarefa());
        TarefaRepository.adicionarTarefa(tarefa);
        System.out.println("----------------------------------------");
        System.out.println("Tarefa criada com sucesso!");
        return tarefa;
    }

    public static int gerarIdParaNovaTarefa() {
        List<Tarefa> tarefas = TarefaRepository.getTarefas();
        return MyUtils.gerarNovoId(tarefas);
    }

    public static void salvarTarefas() {
        File tarefasFile = MyFileWriter.getOrCreateFile("tarefas.txt");
        List<Tarefa> tarefas = TarefaRepository.getTarefas();
        MyFileWriter.writeToFile(tarefas, tarefasFile);
    }

    public static List<Tarefa> filtrarTarefas(Predicate<Tarefa> condition) {
        List<Tarefa> tarefas = TarefaRepository.getTarefas();
        List<Tarefa> tarefasFiltradas = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (condition.test(tarefa)) {
                tarefasFiltradas.add(tarefa);
            }
        }

        return tarefasFiltradas;
    }
}
