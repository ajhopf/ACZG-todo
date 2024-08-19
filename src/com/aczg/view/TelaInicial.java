package com.aczg.view;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaInicial {
    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                gerarMenuInicial();

                int opcaoSelecionada = sc.nextInt();

                if (opcaoSelecionada < 0 || opcaoSelecionada > 2 ) {
                    throw new OpcaoInvalidaException("Navegue pelo menu utilizando os números 0, 1 ou 2");
                }

                switch (opcaoSelecionada) {
                    case 0:
                        System.out.println("Até logo!");
                        sc.close();
                        return;
                    case 1:
                        TelaTarefas.listarTarefas();
                        break;
                    case 2:
                        sc.nextLine();
                        TelaTarefas.adicionarTarefa(sc);
                        break;
                    default: System.out.println("Você escolheu a opção " + opcaoSelecionada);
                }

                iniciar();
            } catch (OpcaoInvalidaException e) {
                MyUtils.printError(e.getMessage());
                sc.nextLine();
            } catch(InputMismatchException e) {
                MyUtils.printError("Você deve utilizar o número da opção para navegar pelo menu");
                sc.nextLine();
            } catch (Exception e) {
                MyUtils.printError(e.toString());
                sc.nextLine();
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
        System.out.println("0. Sair do Sistema");
        System.out.println("-------------------");
        System.out.print("Selecione o número da opção desejada: ");
    }

}

