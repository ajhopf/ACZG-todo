package com.aczg;

import com.aczg.model.Categoria;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyFileWriter;
import com.aczg.utils.MyUtils;
import com.aczg.view.TelaInicial;

import java.io.File;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        MyFileWriter.carregarTarefas(new File("tarefas.txt"));
        TelaInicial.iniciar();
    }
}
