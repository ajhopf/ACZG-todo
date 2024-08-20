package com.aczg.view;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Tarefa;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;
import com.aczg.utils.SortbyPrioridade;

import java.text.SimpleDateFormat;
import java.util.*;

public class ListarTarefas {
    public static void listarTarefas(Scanner sc) {
        while (true) {
            printMenuDaSessao();

            int opcaoSelecionada = MyUtils.getIntInput(0, 3, "Selecione a opção desejada com um número de 0 a 3", sc);

            switch (opcaoSelecionada) {
                case 0:
                    return;
                case 1:
                    ListarTarefas.sortTarefasPorPrioridade();
                    break;
                case 2:
                    AdicionarTarefas.adicionarTarefa(sc);
                    break;
                default: System.out.println("Você escolheu a opção " + opcaoSelecionada);
            }
        }
    }

    private static List<Tarefa> sortTarefasPorPrioridade() {
        List<Tarefa> tarefas = new ArrayList<>(TarefaService.getTarefas());

        tarefas.sort(new SortbyPrioridade());

        tarefas.forEach(tarefa -> printTarefa(tarefa));

        return tarefas;
    }

    private static void printMenuDaSessao() {
        System.out.println("----------------------------------------");
        System.out.println("Lista de Tarefas");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Todas Tarefas por ordem de prioridade");
        System.out.println("2. Listar Tarefas por Status");
        System.out.println("3. Listar Tarefas por Categoria");
        System.out.println("0. Retornar ao menu inicial");
        System.out.println("-------------------");
    }



    private static void printTarefa(Tarefa tarefa) {
        MyUtils.criarCabecalhoDeSessao(tarefa.getNome());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatter.format(tarefa.getDataDeTermino());

        System.out.println("Id: " + tarefa.getId());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data de Término: " + data);
        System.out.println("Prioridade: " + tarefa.getPrioridade());
        System.out.println("Categoria: " + tarefa.getCategoria());
        System.out.println("Status: " + tarefa.getStatus());
    }
}
