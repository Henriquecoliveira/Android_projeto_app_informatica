package com.example.app_informatica;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {
    private List<Produto>listaProdutos;

    public ProdutoAdapter(List<Produto>lista){
        this.listaProdutos = lista;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup pai, int viewType){
        View itemLista = LayoutInflater.from(pai.getContext())
                .inflate(R.layout.item_produtos,pai,false);
        return new ProdutoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder local, int posicao){
        Produto produto = listaProdutos.get(posicao);
        local.nome.setText(produto.nome);
        local.descricao.setText(produto.descricao);
        local.preco.setText("R$ " + String.format("%.3f", produto.preco));
        local.foto.setImageResource(produto.imagemProduto);

    }
    @Override
    public int getItemCount(){
        return listaProdutos.size();
    }
}


