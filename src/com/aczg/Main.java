package com.aczg;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.repository.CategoriaRepository;
import com.aczg.repository.TarefaRepository;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;
import com.aczg.utils.MyUtils;
import com.aczg.view.TelaInicial;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Categoria aczg = CategoriaService.criarCategoria("ACZG");
        TarefaService.criarTarefa("Curso Java", "Ver o curso de Java", MyUtils.parseDate("18/08/2024"), Prioridade.ALTA, aczg, Status.DONE);
        TarefaService.criarTarefa("Todos", "Finalizar projeto de todos", MyUtils.parseDate("19/08/2024"), Prioridade.MUITO_ALTA, aczg, Status.DOING);
        TelaInicial.iniciar();
    }
}
