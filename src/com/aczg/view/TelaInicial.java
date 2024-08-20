package com.aczg.view;

import com.aczg.utils.MyUtils;

import java.util.List;
import java.util.Scanner;

public class TelaInicial {
    public static void iniciar() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            gerarMenuInicial();

            int opcaoSelecionada = MyUtils.getIntInput(0, 4, "Selecione a opção desejada: ", sc);

            switch (opcaoSelecionada) {
                case 0:
                    System.out.println("Até logo!");
                    return;
                case 1:
                    ListarTarefas.listarTarefas(sc);
                    break;
                case 2:
                    ManipularTarefas.adicionarTarefa(sc);
                    break;
                case 3:
                    ManipularTarefas.deletarTarefa(sc);
                    break;
                case 4:
                    ListarTarefas.listarEstatisticas();
                    break;
                default: System.out.println("Você escolheu a opção " + opcaoSelecionada);
            }
        }

    }

    public static void gerarMenuInicial(){
        System.out.println("----------------------------------------");
        System.out.println("Bem vindo ao ACZG - TODO List!");
        System.out.println();
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Listar Tarefas");
        System.out.println("2. Adicionar nova Tarefa");
        System.out.println("3. Deletar Tarefa");
        System.out.println("4. Estatísticas");
        System.out.println("0. Sair do Sistema");
        System.out.println("-------------------");
    }

}

