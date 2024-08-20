package com.aczg.utils;

import com.aczg.model.Tarefa;

import java.util.Comparator;

public class SortbyPrioridade implements Comparator<Tarefa> {

    @Override
    public int compare(Tarefa o1, Tarefa o2) {
        return o2.getPrioridade().getValor() - o1.getPrioridade().getValor();
    }
}
