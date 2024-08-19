package com.aczg.view;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TelaTarefas {
    public static void adicionarTarefa(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Criar nova Tarefa");

        System.out.println("Digite o nome da tarefa:");
        String nome = sc.nextLine();
        System.out.println("Digite a descricao da tarefa:");
        String descricao = sc.nextLine();

        Categoria categoria = obterCategoria(sc);
        Date dataDeTermino = obterData(sc);
        Status status = obterStatus(sc);
        Prioridade prioridade = obterPrioridade(sc);

        TarefaService.criarTarefa(nome, descricao, dataDeTermino, prioridade, categoria, status);
        sc.nextLine();
    }

    private static int getIntInput(int min, int max, String title, Scanner sc) {
        boolean inputIncorreto = true;
        int result = 1;

        System.out.println(title);
        try {
            int value = sc.nextInt();
            if (value < min || value > max) {
                throw new OpcaoInvalidaException("Escolha um número entre " + min + " e " + max);
            }
            inputIncorreto = false;
            result = value;
        } catch (OpcaoInvalidaException e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Você deve escolher utilizando um número de " + min + " a " + max);
            sc.nextLine();
        } finally {
            if (inputIncorreto) {
                getIntInput(min, max, title, sc);
            }
        }

        return result;
    }

    private static Status obterStatus(Scanner sc) {
        int status = getIntInput(1, 3, "Digite o status da tarefa\n1 = TODO; 2 = DOING; 3 = DONE", sc);

        switch (status) {
            case 1:
                return Status.TODO;
            case 2:
                return Status.DOING;
            default:
                return Status.DONE;
        }
    }

    private static Categoria obterCategoria(Scanner sc) {
        Categoria categoria;

        System.out.println("Digite a categoria da tarefa:");
        String nomeDaCategoria = sc.nextLine();

        if (!CategoriaService.categoriaExiste(nomeDaCategoria)) {
            categoria = CategoriaService.criarCategoria(nomeDaCategoria);
        } else {
            categoria = CategoriaService.buscarCategoriaPeloNome(nomeDaCategoria);
        }

        return categoria;
    }

    private static Date obterData(Scanner sc) {
        boolean inputIncorreto = true;
        Date data = null;

        while(inputIncorreto) {
            System.out.println("Digite a data para conclusão da tarefa:");
            try {
                String dataDeTermino = sc.nextLine();
                data = MyUtils.parseDate(dataDeTermino);
                inputIncorreto = false;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Informe uma data válida no formato: dd/MM/yyyy");
            }
        }

        return data;
    }

    private static Prioridade obterPrioridade(Scanner sc) {
        int prioridade = getIntInput(1, 5, "Digite a prioridade da tarefa\nMenor Prioridade = 1 / Maior Prioridade = 5", sc);

        switch (prioridade) {
            case 1:
                return Prioridade.MUITO_BAIXA;
            case 2:
                return Prioridade.BAIXA;
            case 3:
                return Prioridade.MEDIA;
            case 4:
                return Prioridade.ALTA;
            default:
                return Prioridade.MUITO_ALTA;
        }

    }

    public static void listarTarefas() {
        List<Tarefa> tarefas = TarefaService.getTarefas();

        System.out.println("Listagem de Todas as Tarefas por ordem de Inserção: ");

        tarefas.forEach(tarefa -> printTarefa(tarefa));
    }

    private static void printTarefa(Tarefa tarefa) {
        MyUtils.criarCabecalhoDeSessao(tarefa.getNome());
        System.out.println("Id: " + tarefa.getId());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data de Término: " + tarefa.getDataDeTermino());
        System.out.println("Prioridade: " + tarefa.getPrioridade());
        System.out.println("Categoria: " + tarefa.getCategoria());
        System.out.println("Status: " + tarefa.getStatus());
    }
}
