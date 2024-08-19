package com.aczg.utils;

import com.aczg.model.Identificavel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
}
