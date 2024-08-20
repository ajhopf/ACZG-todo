package com.aczg;

import com.aczg.utils.MyFileWriter;
import com.aczg.view.TelaInicial;

import java.io.File;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        MyFileWriter.carregarTarefas(new File("tarefas.txt"));
        TelaInicial.iniciar();
    }
}
