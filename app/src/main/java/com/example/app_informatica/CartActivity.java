package com.example.app_informatica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrinho_main);


        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final Carrinho cart = Carrinho.getInstance();

        TextView txtEmpty = findViewById(R.id.txtEmpty);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCartProducts);
        Button confirmButtom = findViewById(R.id.btnConfirmar);

        if (cart.getProducts().isEmpty()) {
            confirmButtom.setEnabled(false);
            recyclerView.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            confirmButtom.setEnabled(true);
            recyclerView.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

        }



        TextView totalProductsTxt = findViewById(R.id.txtTotalProdutos);
        TextView totalValueTxt = findViewById(R.id.txtTotalPreco);

        CartProductAdapter adapter = new CartProductAdapter(cart, () -> {
            updateSummary(cart, totalProductsTxt, totalValueTxt);

            if (cart.getProducts().isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                txtEmpty.setVisibility(View.VISIBLE);
                confirmButtom.setEnabled(false);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                txtEmpty.setVisibility(View.GONE);
                confirmButtom.setEnabled(true);
            }
        });
        recyclerView.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        double cartPrice = 0;

        List<Produto> products = cart.getProducts();

        for (Produto product : products) {
            cartPrice += product.getPreco();
        }

        totalValueTxt.setText("Valor total: " + formatter.format(cartPrice));
        totalProductsTxt.setText("Total de produtos: " + String.valueOf(products.size()));


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            finish(); // back
        });
    }

    public void onCancel(View view){
            finish(); // back
    }

    private void updateSummary(Carrinho cart, TextView productsText, TextView priceText) {
        int totalProdutos = cart.getProducts().size();

        double totalPreco = 0;
        for (Produto p : cart.getProducts()) {
            totalPreco += p.preco;
        }

        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        productsText.setText("Total de produtos: " + totalProdutos);
        priceText.setText("Total: " + format.format(totalPreco));
    }

    public void openCheckout(View view){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

}