package com.example.app_informatica;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        local.preco.setText(formato.format(produto.preco));
        local.foto.setImageResource(produto.imagemProduto);

        local.itemView.setOnClickListener(v -> {
            Context context = v.getContext();

            BottomSheetDialog dialog = new BottomSheetDialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.pop_up_produto, null);

            ImageView imgPopup = view.findViewById(R.id.imgProdutoGrande);
            imgPopup.setImageResource(produto.imagemProduto);

            TextView tituloPopup = view.findViewById(R.id.txtTituloPopUp);
            tituloPopup.setText(produto.nome);

            TextView descricaoPopup = view.findViewById(R.id.txtDescricaoPopUp);
            descricaoPopup.setText(produto.descricao);

            TextView precoPopup = view.findViewById(R.id.txtPrecoPopUp);
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            precoPopup.setText(format.format(produto.preco));

            dialog.setContentView(view);
            dialog.show();
        });

    }
    @Override
    public int getItemCount(){
        return listaProdutos.size();
    }
}


