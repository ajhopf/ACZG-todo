package com.aczg.repository;

import com.aczg.model.Tarefa;
import com.aczg.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    static private List<Tarefa> tarefas = new ArrayList<>();
    static public void adicionarTarefa(Tarefa tarefa) {tarefas.add(tarefa);}
    static public void atualizarTarefa(int Id, Tarefa tarefaAtualizada) {tarefas.set(Id, tarefaAtualizada);}
    static public void removerTarefa(Tarefa tarefa) {tarefas.remove(tarefa);}
    static public List<Tarefa> getTarefas() {return tarefas;}
}
