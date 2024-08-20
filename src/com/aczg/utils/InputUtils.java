package com.aczg.utils;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Categoria;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    public static int getIntInput(int min, int max, String title, Scanner sc) {
        System.out.println(title);
        System.out.println();
        while (true) {
            try {
                String stringValue = sc.nextLine();

                int value = Integer.parseInt(stringValue);

                if (value < min || value > max) {
                    throw new OpcaoInvalidaException("Escolha um número entre " + min + " e " + max);
                }
                return value;
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Você deve escolher utilizando um número de " + min + " a " + max);
            } catch (NumberFormatException e) {
                System.out.println("Você deve escolher utilizando um número de " + min + " a " + max);
            }
        }
    }

    public static String obterString(String title, Scanner sc) {
        System.out.println(title);
        return sc.nextLine();
    }

    public static Status obterStatus(String title, Scanner sc) {
        int status = getIntInput(1, 3, title , sc);

        switch (status) {
            case 1:
                return Status.TODO;
            case 2:
                return Status.DOING;
            default:
                return Status.DONE;
        }
    }

    public static Categoria obterCategoria(String title, Scanner sc) {
        Categoria categoria;

        System.out.println(title);
        String nomeDaCategoria = sc.nextLine();

        if (!CategoriaService.categoriaExiste(nomeDaCategoria)) {
            categoria = CategoriaService.criarCategoria(nomeDaCategoria);
        } else {
            categoria = CategoriaService.buscarCategoriaPeloNome(nomeDaCategoria);
        }

        return categoria;
    }

    public static Date obterData(String title, Scanner sc) {
        boolean inputIncorreto = true;
        Date data = null;

        while(inputIncorreto) {
            System.out.println(title);
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

    public static Prioridade obterPrioridade(String title, Scanner sc) {
        int prioridade = getIntInput(1, 5, title, sc);

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
