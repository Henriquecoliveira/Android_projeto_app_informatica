package com.example.app_informatica;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        montarRecyclerView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void montarRecyclerView(){
        RecyclerView produtosRecyclerView = findViewById(R.id.recyclerViewProdutos);

        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto("I5 10th", "Processador Intel", 500.00, R.drawable.i5));
        lista.add(new Produto("NVIDIA RTX 5090 ROG", "Placa de Vídeo ASUS RTX5090 ROG Astral Gaming OC NVIDIA GeForce, 32GB, GDDR7, ARGB, G-Sync, Ray Tracing, DLSS 4, HDR - 90YV0LW0-M0NA00", 32.999, R.drawable.rtx5090ti));

        ProdutoAdapter adaptador = new ProdutoAdapter(lista);

        produtosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        produtosRecyclerView.setHasFixedSize(true);
        produtosRecyclerView.setAdapter(adaptador);
    }
}