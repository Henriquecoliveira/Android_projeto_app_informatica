package com.example.app_informatica;

import java.io.Serializable;

public class Produto implements Serializable {
    String nome;
    String descricao;
    double preco;
    int imagemProduto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(int imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto(String nome, String descricao, double preco, int imagemProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemProduto = imagemProduto;
    }
}
