package com.example.app_informatica;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

    // Instanciar AlertDialog
    public void caixaDeAlerta(View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Confirmação de compra");
        alerta.setMessage("Deseja finalizar a sua compra?");
        alerta.setCancelable(true); // Permite fechar clicando fora

        // Botão Negativo (Cancelar)
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        // Botão Positivo (Confirmar)
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Carrinho.getInstance().getProducts().clear(); //limpando tudo
                Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(CheckoutActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_LONG).show();
            }
        });
        alerta.create();
        alerta.show();
    }
}