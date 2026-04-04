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
        lista.add(new Produto("Intel Core I5 14400f 14ª Geração",
                "Processador Intel Core I5 14400f 14ª Geração, 3.5 Ghz (4.7 Ghz Turbo), Cache 20mb, 10 Núcleos, 16 Threads, Lga1700, Sem Vídeo Integrado - Bx8071514400f", 1290.00, R.drawable.i514th));
        lista.add(new Produto("NVIDIA RTX 5090 ROG", "Placa de Vídeo ASUS RTX5090 ROG Astral Gaming OC NVIDIA GeForce, 32GB, GDDR7, ARGB, G-Sync, Ray Tracing, DLSS 4, HDR - 90YV0LW0-M0NA00", 32999.00, R.drawable.rtx5090ti));
        lista.add(new Produto("SSD Nvme Kingston M.2 500GB",
                "SSD Kingston Nv3 500 Gb Nvme M.2 2280 Leitura 5000mb/s Gravação 6000mb/s- Snv3s/500g",
                722.69, R.drawable.ssdkingston));

        ProdutoAdapter adaptador = new ProdutoAdapter(lista);

        produtosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        produtosRecyclerView.setHasFixedSize(true);
        produtosRecyclerView.setAdapter(adaptador);
    }
}