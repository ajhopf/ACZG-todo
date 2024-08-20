package com.aczg.view;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Tarefa;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;
import com.aczg.utils.SortbyPrioridade;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ListarTarefas {
    public static void listarTarefas(Scanner sc) {
        while (true) {
            printMenuDaSessao();

            int opcaoSelecionada = MyUtils.getIntInput(0, 4, "Selecione a opção desejada com um número de 0 a 3", sc);

            switch (opcaoSelecionada) {
                case 0:
                    return;
                case 1:
                    ListarTarefas.listarTodasTarefasSortedPorPrioridade();
                    break;
                case 2:
                    ListarTarefas.listarTarefasPorPrioridade(sc);
                    break;
                default: System.out.println("Você escolheu a opção " + opcaoSelecionada);
            }
        }
    }

    private static List<Tarefa> listarTodasTarefasSortedPorPrioridade() {
        List<Tarefa> tarefas = new ArrayList<>(TarefaService.getTarefas());

        tarefas.sort(new SortbyPrioridade());

        tarefas.forEach(tarefa -> printTarefa(tarefa));

        return tarefas;
    }

    public static void listarTarefasPorPrioridade(Scanner sc) {
        System.out.println("----------------------------------------");
        System.out.println("Listar Tarefas por Prioridade");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Tarefas com prioridade 1");
        System.out.println("2. Listar Tarefas com prioridade 2");
        System.out.println("3. Listar Tarefas com prioridade 3");
        System.out.println("4. Listar Tarefas com prioridade 4");
        System.out.println("5. Listar Tarefas com prioridade 5");
        System.out.println("0. Voltar");
        System.out.println("-------------------");

        int opcaoSelecionada = MyUtils.getIntInput(0, 5, "Selecione a opção desejada:", sc);

        filtrarTarefasPorPrioridade(opcaoSelecionada);
    }

    private static void filtrarTarefasPorPrioridade(int prioridadeValue) {
        List<Tarefa> tarefas = new ArrayList<>(TarefaService.getTarefas());
        List<Tarefa> tarefasFiltradas = tarefas
                .stream()
                .filter(tarefa -> tarefa.getPrioridade().getValor() == prioridadeValue)
                .collect(Collectors.toList());

        tarefasFiltradas.forEach(tarefa -> printTarefa(tarefa));
    }

    private static void printMenuDaSessao() {
        System.out.println("----------------------------------------");
        System.out.println("Lista de Tarefas");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Todas Tarefas por ordem de prioridade");
        System.out.println("2. Listar Tarefas por prioridade");
        System.out.println("3. Listar Tarefas por Status");
        System.out.println("4. Listar Tarefas por Categoria");
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
