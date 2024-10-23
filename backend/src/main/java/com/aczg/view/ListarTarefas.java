package com.aczg.view;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.InputUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static com.aczg.utils.MyUtils.dateToString;
import static com.aczg.utils.MyUtils.printTarefa;

public class ListarTarefas {
    public static void listarTarefas(Scanner sc) {
        while (true) {
            printMenuDaSessao();

            int opcaoSelecionada = InputUtils.getIntInput(0, 4, "Selecione a opção desejada com um número de 0 a 4", sc);

            switch (opcaoSelecionada) {
                case 0:
                    return;
                case 1:
                    listarTodasTarefas(sc);
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

    public static void listarEstatisticas() {
        int tarefasTodo = TarefaService.filtrarTarefas((t) -> t.getStatus() == Status.TODO).size();
        int tarefasDoing = TarefaService.filtrarTarefas((t) -> t.getStatus() == Status.DOING).size();
        int tarefasDone = TarefaService.filtrarTarefas((t) -> t.getStatus() == Status.DONE).size();

        System.out.println("----------------------------------------");
        System.out.println("Estatísticas");
        System.out.println();
        System.out.println("Tarefas a Fazer (TODO): " + tarefasTodo);
        System.out.println("Tarefas em Progresso (DOING): " + tarefasDoing);
        System.out.println("Tarefas Finalizadas (DONE): " + tarefasDone);
        System.out.println("-------------------");
    }

    private static void printMenuDaSessao() {
        System.out.println("----------------------------------------");
        System.out.println("Lista de Tarefas");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Todas Tarefas");
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

    private static void listarTodasTarefas(Scanner sc) {
        List<Tarefa> tarefas = new ArrayList<>(TarefaService.getTarefas());

        System.out.println("----------------------------------------");
        System.out.println("Você deseja filtrar as tarefas por data?");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.println("-------------------");

        int opcaoSelecionada = InputUtils.getIntInput(1, 2, "Selecione a opção desejada:", sc);

        switch (opcaoSelecionada) {
            case 1:
                filtrarPorData(tarefas, sc);
                break;
            case 2:
                tarefas.forEach(tarefa -> printTarefa(tarefa));
                break;
        }
    }

    public static void filtrarPorData(List<Tarefa> tarefas, Scanner sc) {
        System.out.println("----------------------------------------");
        System.out.println("Como você deseja realizar o filtro");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Selecionar apenas tarefas com data de término idêntica a data que eu indicar");
        System.out.println("2. Selecionar tarefas que tenham data de término após a data que eu indicar");
        System.out.println("-------------------");

        int opcaoSelecionada = InputUtils.getIntInput(1, 2, "Selecione a opção desejada:", sc);
        Date data = InputUtils.obterData("Digite a data: ", sc);

        switch (opcaoSelecionada) {
            case 1:
                List<Tarefa> tarefasFiltradas = TarefaService.filtrarTarefas(t -> t.getDataDeTermino().compareTo(data) == 0);
                if(tarefasFiltradas.isEmpty()) {
                    System.out.println("Você não possui tarefas cadastradas para o dia " + dateToString(data));
                } else {
                    tarefasFiltradas.forEach(t -> printTarefa(t));
                }
                break;
            default:
                List<Tarefa> tarefasFiltradas2 = TarefaService.filtrarTarefas(t -> t.getDataDeTermino().compareTo(data) > 0);
                if(tarefasFiltradas2.isEmpty()) {
                    System.out.println("Você não possui tarefas cadastradas para depois do dia " + dateToString(data));
                } else {
                    tarefasFiltradas2.forEach(t -> printTarefa(t));
                }
                break;
        }
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

        int opcaoSelecionada = InputUtils.getIntInput(0, 3, "Selecione a opção desejada:", sc);

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

        int valorDePrioridade = InputUtils.getIntInput(0, 5, "Selecione a opção desejada:", sc);

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

        int categoria = InputUtils.getIntInput(0, numeroDeCategorias.get(), "Selecione a opção desejada:", sc);

        if (categoria == 0) {
            return;
        }

        filtrarTarefas((t) -> t.getCategoria() == categorias.get(categoria-1));
    }
}
