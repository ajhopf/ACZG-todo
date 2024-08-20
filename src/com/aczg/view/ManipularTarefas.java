package com.aczg.view;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.InputUtils;
import com.aczg.utils.MyUtils;
import jdk.internal.util.xml.impl.Input;

import java.text.ParseException;
import java.util.*;

public class ManipularTarefas {
    public static void deletarTarefa(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Deletar Tarefa");
        System.out.println("Escolha uma das tarefas abaixo pelo ID para deletar");

        int idSelecionado = selecionarTarefaById(sc);

        if (idSelecionado == -1) {
            return;
        }

        Optional<Tarefa> tarefaSelecionada = TarefaService
                .filtrarTarefas(t -> t.getId() == idSelecionado)
                .stream()
                .findFirst();

        if (tarefaSelecionada.isPresent()) {
            TarefaService.removerTarefa(tarefaSelecionada.get());
            System.out.println("Tarefa deletada com sucesso!");
        } else {
            System.out.println("Não foi possível selecionar uma tarefa com o ID informado. Tente novamente");
        }
    }

    public static void atualizarTarefa(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Atualizar Tarefa");
        System.out.println("Escolha uma das tarefas abaixo pelo ID para editar");

        int idSelecionado = selecionarTarefaById(sc);

        if (idSelecionado == -1) {
            return;
        }

        Optional<Tarefa> tarefaSelecionada = TarefaService
                .filtrarTarefas(t -> t.getId() == idSelecionado)
                .stream()
                .findFirst();

        if (tarefaSelecionada.isPresent()) {
            efetuarEdicaoDeCampoEmTarefa(tarefaSelecionada.get(), sc);
        } else {
            System.out.println("Não foi possível selecionar uma tarefa com o ID informado. Tente novamente");
        }
    }

    private static void efetuarEdicaoDeCampoEmTarefa(Tarefa tarefaAntiga, Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Atualizar Tarefa: ");

        MyUtils.printTarefa(tarefaAntiga);
        System.out.println("-------------------");

        System.out.println("Qual campo você deseja editar?");
        System.out.println("1. Nome");
        System.out.println("2. Descrição");
        System.out.println("3. Data de Término");
        System.out.println("4. Prioridade");
        System.out.println("5. Categoria");
        System.out.println("6. Status");
        System.out.println("0. Cancelar");
        System.out.println("-------------------");

        int opcao = InputUtils.getIntInput(0, 6, "Digite a opção desejada", sc);
        Tarefa tarefaAtualizada = new Tarefa(tarefaAntiga);

        switch (opcao) {
            case 1:
                System.out.println("Atualizando nome da tarefa: " + tarefaAntiga.getNome());
                String novoNome = InputUtils.obterString("Digite o novo nome para a tarefa: ", sc);
                tarefaAtualizada.setNome(novoNome);
                break;
            case 2:
                System.out.println("Atualizando descrição da tarefa: " + tarefaAntiga.getNome());
                String novaDescricao = InputUtils.obterString("Digite a nova descrição para a tarefa: ", sc);
                tarefaAtualizada.setDescricao(novaDescricao);
                break;
            case 3:
                System.out.println("Atualizando data de término da tarefa: " + tarefaAntiga.getNome());
                Date novaData = InputUtils.obterData("Digite a nova data de término para a tarefa: ", sc);
                tarefaAtualizada.setDataDeTermino(novaData);
                break;
            case 4:
                System.out.println("Atualizando a prioridade da tarefa: " + tarefaAntiga.getNome());
                Prioridade novaPrioridade = InputUtils.obterPrioridade("Digite a nova prioridade da tarefa\nMenor Prioridade = 1 / Maior Prioridade = 5", sc);
                tarefaAtualizada.setPrioridade(novaPrioridade);
                break;
            case 5:
                System.out.println("Atualizando a categoria da tarefa: " + tarefaAntiga.getNome());
                Categoria novaCategoria = InputUtils.obterCategoria("Digite a nova categoria da tarefa:", sc);
                tarefaAtualizada.setCategoria(novaCategoria);
                break;
            case 6:
                System.out.println("Atualizando o status da tarefa: " + tarefaAntiga.getNome());
                Status novoStatus = InputUtils.obterStatus("Digite o novo status da tarefa\n1 = TODO; 2 = DOING; 3 = DONE", sc);
                tarefaAtualizada.setStatus(novoStatus);
                break;
            default:
                System.out.println("Atualização cancelada.");
                return;
        }

        TarefaService.atualizarTarefa(tarefaAtualizada);
        System.out.println("Tarefa atualizada com sucesso!");
        MyUtils.printTarefa(tarefaAtualizada);
    };

    private static int selecionarTarefaById(Scanner sc) {
        List<Tarefa> tarefas = TarefaService.getTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("Você não possui tarefas cadastradas!");
            return -1;
        }

        List<Integer> idList = new ArrayList<>();

        tarefas.forEach(tarefa -> {
            MyUtils.printTarefa(tarefa);
            idList.add(tarefa.getId());
        });

        int maiorId = idList.stream().max(Integer::compareTo).get();
        int menorId = idList.stream().min(Integer::compareTo).get();
        boolean validIdSelected = false;

        int idSelecionado = 0;

        while (!validIdSelected) {
            idSelecionado = InputUtils.getIntInput(-1, maiorId, "Selecione a opção desejada com um número de " + menorId+ " a " + maiorId + ". Ou digite -1 para cancelar", sc);

            if (idSelecionado == -1 ) {
                return -1;
            }
            if (idList.contains(idSelecionado)) {
                validIdSelected = true;
            } else {
                System.out.println("Você selecionou um Id que não é válido! Tente novamente.");
                System.out.println("Possíveis IDs para deletar:");

                idList.forEach(id -> System.out.print(id + " - "));
                System.out.println();
            }
        }

        return idSelecionado;
    }

    public static void adicionarTarefa(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Criar nova Tarefa");

        String nome = InputUtils.obterString("Digite o nome da tarefa", sc);
        String descricao = InputUtils.obterString("Digite a descricao da tarefa:", sc);
        Categoria categoria = InputUtils.obterCategoria("Digite a categoria da tarefa:", sc);
        Date dataDeTermino = InputUtils.obterData("Digite a data para conclusão da tarefa:", sc);
        Status status = InputUtils.obterStatus("Digite o status da tarefa\n1 = TODO; 2 = DOING; 3 = DONE", sc);
        Prioridade prioridade = InputUtils.obterPrioridade("Digite a prioridade da tarefa\nMenor Prioridade = 1 / Maior Prioridade = 5", sc);

        TarefaService.criarTarefa(nome, descricao, dataDeTermino, prioridade, categoria, status);
    }

}
