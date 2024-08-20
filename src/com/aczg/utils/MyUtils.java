package com.aczg.utils;

import com.aczg.exceptions.OpcaoInvalidaException;
import com.aczg.model.Identificavel;
import com.aczg.model.Tarefa;

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

    public static void printTarefa(Tarefa tarefa) {
        MyUtils.criarCabecalhoDeSessao(tarefa.getNome());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatter.format(tarefa.getDataDeTermino());

        System.out.println("Id: " + tarefa.getId());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data de Término: " + data);
        System.out.println("Prioridade: " + tarefa.getPrioridade());
        System.out.println("Categoria: " + tarefa.getCategoria());
        System.out.println("Status: " + tarefa.getStatus());
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
