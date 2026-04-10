package com.example.app_informatica;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {
    private List<Produto>listaProdutos;
    private Carrinho cart;

    public ProdutoAdapter(List<Produto>lista, Carrinho cart){
        this.listaProdutos = lista;
        this.cart = cart;
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
        local.foto.setImageResource(produto.imagemProduto);
        local.preco.setText(formato.format(produto.preco));
        local.desconto.setText("De: " + formato.format(produto.preco * 1.3));


        local.itemView.setOnClickListener(v -> {
            Context context = v.getContext();

            BottomSheetDialog dialog = new BottomSheetDialog(context);
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            View view = LayoutInflater.from(context).inflate(R.layout.pop_up_produto, null);

            ImageView imgPopup = view.findViewById(R.id.imgProdutoGrande);
            imgPopup.setImageResource(produto.imagemProduto);

            TextView tituloPopup = view.findViewById(R.id.txtTituloPopUp);
            tituloPopup.setText(produto.nome);

            TextView descricaoPopup = view.findViewById(R.id.txtDescricaoPopUp);
            descricaoPopup.setText(produto.descricao);

            TextView precoPopup = view.findViewById(R.id.txtPrecoPopUp);
            precoPopup.setText(formatter.format(produto.preco));

            TextView disccountPopup = view.findViewById(R.id.priceTextWithoutDiscount);
            disccountPopup.setText("De: " + formatter.format(produto.preco * 1.3));

            TextView txtQuantidade = view.findViewById(R.id.txtQuantidade);
            ImageButton addQuantidade = view.findViewById(R.id.imgButtomAdicionar);
            ImageButton removeQuantidade = view.findViewById(R.id.imgButtomRemove);

            Button addToCartButton = view.findViewById(R.id.buttomAddCarrinho);

            final int[] quantidade = {1};
            txtQuantidade.setText(String.valueOf(quantidade[0]));

            double preco = produto.preco * quantidade[0];
            precoPopup.setText(formatter.format(preco));

            addQuantidade.setOnClickListener(view1 -> {
                quantidade[0]++;
                txtQuantidade.setText(String.valueOf(quantidade[0]));

                double total = produto.preco * quantidade[0];
                precoPopup.setText(formatter.format(total));
            });

            removeQuantidade.setOnClickListener(view2 ->{
                if(quantidade[0] > 1){
                    quantidade[0]--;
                    txtQuantidade.setText(String.valueOf(quantidade[0]));

                    double total = produto.preco * quantidade[0];
                    precoPopup.setText(formatter.format(total));
                }
            });

            addToCartButton.setOnClickListener(view3 -> {
                if (this.cart != null) {
                    System.out.println("adicionado ");
                    this.cart.addProduct(produto, quantidade[0]);
                    Toast.makeText(view3.getContext(), "Item Adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                    dialog.hide();
                } else {
                    Toast.makeText(view3.getContext(), "Erro ao adicionar item", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.setContentView(view);
            dialog.show();
        });

    }

    @Override
    public int getItemCount(){
        return listaProdutos.size();
    }

    public void updateList(List<Produto> newList) {
        this.listaProdutos = newList;
        notifyDataSetChanged();
    }

}


