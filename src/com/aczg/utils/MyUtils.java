package com.aczg.utils;

import com.aczg.model.Identificavel;
import com.aczg.model.Tarefa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyUtils {
    public static void printTarefa(Tarefa tarefa) {
        MyUtils.criarCabecalhoDeSessao("Nome: " + tarefa.getNome());

        String data = dateToString(tarefa.getDataDeTermino());

        System.out.println("Id: " + tarefa.getId());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data de Término: " + data);
        System.out.println("Prioridade: " + tarefa.getPrioridade());
        System.out.println("Categoria: " + tarefa.getCategoria().getCategoria());
        System.out.println("Status: " + tarefa.getStatus());
        System.out.println(" ");
    }

    public static void criarCabecalhoDeSessao(String title) {
        System.out.println("-------------------------------------------------");
        System.out.println(title);
        System.out.println("-------------------------------------------------");
    }

    public static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static <T extends Identificavel> int gerarNovoId(List<T> list) {
        int newId;
        if (list.size() > 0) {
            int maiorId = 0;

            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                if (t.getId() > maiorId) {
                    maiorId = t.getId();
                }
            }

            newId = maiorId + 1;
        } else {
            newId = 0;
        }
        return newId;
    }
}
