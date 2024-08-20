package com.aczg.utils;

import com.aczg.model.Categoria;
import com.aczg.model.Tarefa;
import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;
import com.aczg.service.CategoriaService;
import com.aczg.service.TarefaService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyFileWriter {
    static public File buscarOuCriarArquivo(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Arquivo criado: " + file.getName());
            }
            return file;
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao procurar o arquivo. " + e.getMessage());
            return null;
        }
    }

    static public void salvarNoArquivo(List<Tarefa> list, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Tarefa t : list) {
                writer.write(t.save());
                writer.newLine();
            }
            System.out.println("O arquivo foi salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao gravar as tarefas no arquivo.");
            e.printStackTrace();
        }
    }
    static public List<Tarefa> carregarTarefas(File file) {
        List<Tarefa> tarefas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");

                if (campos.length == 6) {
                    String nome = campos[0];
                    String descricao = campos[1];
                    Date dataDeTermino = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(campos[2]);
                    Prioridade prioridade = Prioridade.valueOf(campos[3].toUpperCase().replace(" ", "_"));
                    String nomeDaCategoria = campos[4];
                    Categoria categoria;
                    if (!CategoriaService.categoriaExiste(nomeDaCategoria)) {
                        categoria = CategoriaService.criarCategoria(nomeDaCategoria);
                    } else {
                        categoria = CategoriaService.buscarCategoriaPeloNome(nomeDaCategoria);
                    }
                    Status status = Status.valueOf(campos[5]);
                    TarefaService.criarTarefa(nome, descricao, dataDeTermino, prioridade, categoria, status);
                }
            }
            System.out.println("----------------------------------------");
            System.out.println("Tarefas importadas com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível encontrar o arquivo solicitado. Iniciando programa com lista de tarefas vazia.");
        } catch (ParseException e) {
            System.out.println("Ocorreu um erro ao fazer a transferência dos dados do arquivo para o programa.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao fazer a transferência dos dados do arquivo para o programa.");
        }
        return tarefas;
    }

}
