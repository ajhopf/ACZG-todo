package com.aczg.view;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;

import java.text.ParseException;
import java.util.*;

public class ManipularTarefas {
    public static void deletarTarefa(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Deletar Tarefa");

        List<Tarefa> tarefas = TarefaService.getTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("Você não possui tarefas cadastradas!");
            return;
        }

        List<Integer> idList = new ArrayList<>();

        tarefas.forEach(tarefa -> {
            MyUtils.printTarefa(tarefa);
            idList.add(tarefa.getId());
        });

        int maiorId = idList.stream().max(Integer::compareTo).get();
        int menorId = idList.stream().min(Integer::compareTo).get();
        boolean validIdSelected = false;

        int opcaoSelecionada = 0;

        while (!validIdSelected) {
            opcaoSelecionada = MyUtils.getIntInput(-1, maiorId, "Selecione a opção desejada com um número de " + menorId+ " a " + maiorId + ". Ou digite -1 para cancelar", sc);

            if (opcaoSelecionada == -1 ) {
                return;
            }
            if (idList.contains(opcaoSelecionada)) {
                validIdSelected = true;
            } else {
                System.out.println("Você selecionou um Id que não é válido! Tente novamente.");
                System.out.println("Possíveis IDs para deletar:");

                idList.forEach(id -> System.out.print(id + " - "));
                System.out.println();
            }
        }

        int opcaoSelecionadaFinal = opcaoSelecionada;

        Optional<Tarefa> tarefaSelecionada = tarefas
                .stream()
                .filter((t) -> t.getId() == opcaoSelecionadaFinal)
                .findFirst();

        if (tarefaSelecionada.isPresent()) {
            TarefaService.removerTarefa(tarefaSelecionada.get());
            System.out.println("Tarefa deletada com sucesso!");
        } else {
            System.out.println("Não foi possível selecionar uma tarefa com o ID informado. Tente novamente");
        }
    }

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
    }

    private static Status obterStatus(Scanner sc) {
        int status = MyUtils.getIntInput(1, 3, "Digite o status da tarefa\n1 = TODO; 2 = DOING; 3 = DONE", sc);

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
        int prioridade = MyUtils.getIntInput(1, 5, "Digite a prioridade da tarefa\nMenor Prioridade = 1 / Maior Prioridade = 5", sc);

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

}
