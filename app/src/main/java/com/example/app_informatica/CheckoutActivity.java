package com.example.app_informatica;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        Carrinho cart = (Carrinho) Carrinho.getInstance();

        Spinner spinner = findViewById(R.id.spinnerPagamento);

        List<String> opcoes = new ArrayList<>();
        opcoes.add("Pix");
        opcoes.add("Cartão");
        opcoes.add("Boleto");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opcoes
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        ImageView firstProductImage = findViewById(R.id.firstCardProduct);

        if(!cart.getProducts().isEmpty()){
            Produto firstProduct = cart.getProducts().get(0);
            firstProductImage.setImageResource(firstProduct.getImagemProduto());
        }


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

}