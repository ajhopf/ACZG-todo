package com.aczg.model;

import com.aczg.model.enums.Prioridade;
import com.aczg.model.enums.Status;

import java.util.Date;

public class Tarefa extends Identificavel {
    private String nome;
    private String descricao;
    private Date dataDeTermino;
    private Prioridade prioridade;
    private Categoria categoria;
    private Status status;

    public Tarefa(String nome, String descricao, Date dataDeTermino, Prioridade prioridade, Categoria categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeTermino = dataDeTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public Tarefa(Tarefa tarefa) {
       this.nome = tarefa.getNome();
       this.descricao = tarefa.getDescricao();
       this.dataDeTermino = tarefa.getDataDeTermino();
       this.prioridade = tarefa.getPrioridade();
       this.categoria = tarefa.getCategoria();
       this.status = tarefa.getStatus();
       super.setId(tarefa.getId());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDataDeTermino(Date dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + super.getId() +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataDeTermino=" + dataDeTermino +
                ", prioridade=" + prioridade +
                ", categoria=" + categoria +
                ", status=" + status +
                '}';
    }
}
