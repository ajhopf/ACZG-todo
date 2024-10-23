package com.aczg.view;

import com.aczg.model.Categoria;
import com.aczg.service.CategoriaService;
import com.aczg.utils.InputUtils;
import com.aczg.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManipularCategoria {
    public static void deletarCategoria(Scanner sc) {
        MyUtils.criarCabecalhoDeSessao("Deletar Categoria");
        System.out.println("Escolha uma das categorias abaixo pelo ID para deletar");

        int idSelecionado = selecionarCategoriaById(sc);

        if (idSelecionado == -1) {
            return;
        }

        CategoriaService.removerCategoria(idSelecionado);
    }

    private static int selecionarCategoriaById(Scanner sc) {
        List<Categoria> categorias = CategoriaService.listarCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Você não possui categorias cadastradas!");
            return -1;
        }

        List<Integer> idList = new ArrayList<>();

        categorias.forEach(categoria -> {
            System.out.println("Categoria: " + categoria);
            System.out.println("Id: " + categoria.getId());
            System.out.println("--------------------");
            idList.add(categoria.getId());
        });

        int maiorId = idList.stream().max(Integer::compareTo).get();
        int menorId = idList.stream().min(Integer::compareTo).get();
        boolean validIdSelected = false;

        int idSelecionado = 0;

        while (!validIdSelected) {
            idSelecionado = InputUtils.getIntInput(-1, maiorId, "Selecione a opção desejada com um número de " + menorId+ " a " + maiorId + ". Ou digite -1 para cancelar", sc);

            if (idSelecionado == -1 ) {
                return -1;
            }
            if (idList.contains(idSelecionado)) {
                validIdSelected = true;
            } else {
                System.out.println("Você selecionou um Id que não é válido! Tente novamente.");
                System.out.println("Possíveis IDs para deletar:");

                idList.forEach(id -> System.out.print(id + " - "));
                System.out.println();
            }
        }

        return idSelecionado;
    }
}
