package com.aczg.view;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;
import com.aczg.utils.SortbyPrioridade;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static com.aczg.utils.MyUtils.printTarefa;

public class ListarTarefas {
    public static void listarTarefas(Scanner sc) {
        while (true) {
            printMenuDaSessao();

            int opcaoSelecionada = MyUtils.getIntInput(0, 4, "Selecione a opção desejada com um número de 0 a 3", sc);

            switch (opcaoSelecionada) {
                case 0:
                    return;
                case 1:
                    listarTodasTarefasSortedPorPrioridade();
                    break;
                case 2:
                    listarTarefasPorPrioridade(sc);
                    break;
                case 3:
                    listarTarefasPorStatus(sc);
                    break;
                case 4:
                    listarTarefasPorCategoria(sc);
                    break;
                default: System.out.println("Você escolheu a opção " + opcaoSelecionada);
            }
        }
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

    private static void filtrarTarefas(Predicate<Tarefa> filter) {
        List<Tarefa> tarefasFiltradas = TarefaService.filtrarTarefas(filter);

        tarefasFiltradas.forEach(tarefa -> printTarefa(tarefa));
    }

    private static List<Tarefa> listarTodasTarefasSortedPorPrioridade() {
        List<Tarefa> tarefas = new ArrayList<>(TarefaService.getTarefas());

        tarefas.sort(new SortbyPrioridade());

        tarefas.forEach(tarefa -> printTarefa(tarefa));

        return tarefas;
    }

    public static void listarTarefasPorStatus(Scanner sc) {
        System.out.println("----------------------------------------");
        System.out.println("Listar Tarefas por Status");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Tarefas com Status TODO");
        System.out.println("2. Listar Tarefas com Status DOING");
        System.out.println("3. Listar Tarefas com Status DONE");
        System.out.println("0. Voltar");
        System.out.println("-------------------");

        int opcaoSelecionada = MyUtils.getIntInput(0, 3, "Selecione a opção desejada:", sc);

        switch (opcaoSelecionada) {
            case 0:
                return;
            case 1:
                filtrarTarefas((t) -> t.getStatus().equals(Status.TODO));
                break;
            case 2:
                filtrarTarefas((t) -> t.getStatus().equals(Status.DOING));
                break;
            default:
                filtrarTarefas((t) -> t.getStatus().equals(Status.DONE));
        }
    }

    private static void listarTarefasPorPrioridade(Scanner sc) {
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

        int valorDePrioridade = MyUtils.getIntInput(0, 5, "Selecione a opção desejada:", sc);

        if (valorDePrioridade == 0) {
            return;
        }

        filtrarTarefas((t) -> t.getPrioridade().getValor() == valorDePrioridade);
    }

    private static void listarTarefasPorCategoria(Scanner sc) {
        System.out.println("----------------------------------------");
        System.out.println("Listar Tarefas por Categoria");
        System.out.println();

        List<Categoria> categorias = CategoriaService.listarCategorias();

        System.out.println("Selecione uma das opções abaixo:");
        AtomicInteger numeroDeCategorias = new AtomicInteger();

        categorias.forEach(categoria -> {
            System.out.println(numeroDeCategorias.get() +1 + ". Categoria: " + categoria);
            numeroDeCategorias.getAndIncrement();
        });

        System.out.println("0. Voltar");
        System.out.println("-------------------");

        int categoria = MyUtils.getIntInput(0, numeroDeCategorias.get(), "Selecione a opção desejada:", sc);

        if (categoria == 0) {
            return;
        }

        filtrarTarefas((t) -> t.getCategoria() == categorias.get(categoria-1));
    }


}
