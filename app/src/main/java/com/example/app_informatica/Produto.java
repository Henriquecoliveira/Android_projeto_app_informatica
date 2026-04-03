package com.example.app_informatica;

public class Produto {
    String nome;
    String descricao;
    double preco;
    int imagemProduto;

    public Produto(String nome, String descricao, double preco, int imagemProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemProduto = imagemProduto;
    }
}
