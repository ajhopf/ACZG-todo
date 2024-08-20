package com.aczg.utils;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Identificavel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MyUtils {
    public static void printError(String message) {
        System.out.println("-------------------------------------------------");
        System.out.println("Opção inválida, tente novamente.");
        System.out.println("Mensagem:");
        System.out.println(message);
        System.out.println("-------------------------------------------------");
    }

    public static void criarCabecalhoDeSessao(String title) {
        System.out.println("-------------------------------------------------");
        System.out.println(title);
        System.out.println("-------------------------------------------------");
    }

    public static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    public static <T extends Identificavel> int gerarNovoId(List<T> list) {
        int newId;
        if (list.size() > 0) {
            int lastId = list.get(list.size() - 1).getId();
            newId = lastId + 1;
        } else {
            newId = 0;
        }
        return newId;
    }

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
}
