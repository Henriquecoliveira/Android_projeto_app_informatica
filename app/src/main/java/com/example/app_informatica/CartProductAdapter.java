package com.example.app_informatica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartProductAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {
    private Carrinho cart;
    private OnCartChangedListener listener;
    public interface OnCartChangedListener {
        void onCartUpdated();
    }
    public CartProductAdapter(Carrinho cart, OnCartChangedListener listener){
        this.cart = cart;
        this.listener = listener;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup pai, int viewType){
        View itemLista = LayoutInflater.from(pai.getContext())
                .inflate(R.layout.cart_product,pai,false);
        return new ProdutoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder local, int posicao){

        Produto produto = this.cart.getProducts().get(posicao);
        local.nome.setText(produto.nome);
        local.descricao.setText(produto.descricao);
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        local.preco.setText(formatter.format(produto.preco));
        local.foto.setImageResource(produto.imagemProduto);

        Button removeFromCartButton = local.itemView.findViewById(R.id.removeFromCartButton);

        removeFromCartButton.setOnClickListener(view -> {
            int position = local.getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                cart.getProducts().remove(position);
                notifyItemRemoved(position);

                if (listener != null) {
                    listener.onCartUpdated();
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return this.cart.getProducts().size();
    }
}


