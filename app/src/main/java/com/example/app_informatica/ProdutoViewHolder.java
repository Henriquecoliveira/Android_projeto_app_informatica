package com.example.app_informatica;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {
    TextView nome, descricao, preco;
    ImageView foto;

    public ProdutoViewHolder(View itemView){
        super(itemView);
        nome = itemView.findViewById(R.id.txtNomeProduto);
        descricao = itemView.findViewById(R.id.txtDescricaoProduto);
        preco = itemView.findViewById(R.id.txtPreco);
        foto = itemView.findViewById(R.id.imgProdutos);

    }
}
