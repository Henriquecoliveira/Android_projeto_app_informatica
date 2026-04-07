package com.example.app_informatica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    private Carrinho cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.cart = Carrinho.getInstance();
        montarRecyclerView();

    }
    public void montarRecyclerView(){
        RecyclerView produtosRecyclerView = findViewById(R.id.recyclerViewProdutos);
//        RecyclerView cartProductsReciclerView = findViewById(R.id.recyclerViewCartProducts);

        List<Produto> lista = new ArrayList<>();

        lista.add(new Produto("Intel Core I5 14400f 14ª Geração",
                "Processador Intel Core I5 14400f 14ª Geração, 3.5 Ghz (4.7 Ghz Turbo), Cache 20mb, 10 Núcleos, 16 Threads, Lga1700, Sem Vídeo Integrado - Bx8071514400f",
                1290.00, R.drawable.i514th));

        lista.add(new Produto("Intel Core I3 12100f 12ª Geração",
                "Processador Intel Core i3-12100F, 4-Core, 8-Threads, 3.3GHz ( 4.3GHz Turbo), Cache 12MB, LGA1700, BX8071512100F-BR",
                499.99, R.drawable.i312th));

        lista.add(new Produto("Amd Ryzen 5 5600",
                "Processador AMD Ryzen 5 5600, 6-Core, 12-Threads, 3.5GHz (4.4GHz Turbo), Cache 35MB, AM4, 100-100000927BOX",
                1018.00, R.drawable.ryzen55600));

        lista.add(new Produto("Amd Ryzen 7 7800x3D",
                "Processador AMD Ryzen 7 7800X3D, 5.0GHz Max Turbo, Cache 104MB, AM5, 8 Núcleos, Vídeo Integrado - 100-100000910WOF",
                2289.99, R.drawable.ryzen77800x3d));

        lista.add(new Produto("Intel Core Ultra 9 285K",
                "Processador Intel Core Ultra 9-285K, 5.7GHz, Até 24 Núcleos, Com suporte a PCIe 5.0 e 4.0 e suporte a DDR5 - BX80768285K",
                3599.99, R.drawable.i9ultra));

        lista.add(new Produto("NVIDIA RTX 3050 MSI",
                "Placa de Video MSI GeForce RTX 3050 Ventus OC 2X, 6GB, GDDR6, 96-bit, 912-V812-060",
                1349.99, R.drawable.rtx3050msi));

        lista.add(new Produto("AMD Radeon RX 6600 ASRock",
                "Placa de Vídeo ASRock RX 6600 Challenger White AMD Radeon, 8GB, GDDR6, DirectX 12 Ultimate, RDNA 2 - 90-GA4UZZ-00UANF",
                1499.99, R.drawable.rx6600));

        lista.add(new Produto("NVIDIA RTX 4060 ASUS",
                "Placa de vídeo RTX 4060 ASUS Dual 8G EVO OC NVIDIA GeForce, 8GB GDDR6, G-SYNC, Ray Tracing - 90YV0JC7-M0NA00",
                2199.99, R.drawable.rtx4060));

        lista.add(new Produto("NVIDIA RTX 5070 Ti Gigabyte",
                "Placa de Vídeo Gigabyte RTX 5070 Ti GAMING OC 16G NVIDIA GeForce, 16GB GDDR7, 256bits, RGB, DLSS, Ray Tracing - 9VN507TGO-00-G10",
                9999.99, R.drawable.rtx5070ti));

        lista.add(new Produto("NVIDIA RTX 5090 ASUS ROG",
                "Placa de Vídeo ASUS RTX5090 ROG Astral Gaming OC NVIDIA GeForce, 32GB, GDDR7, ARGB, G-Sync, Ray Tracing, DLSS 4, HDR - 90YV0LW0-M0NA00",
                32999.00, R.drawable.rtx5090ti));

        lista.add(new Produto("Placa Mãe MSI Pro H610M-S",
                "Placa Mãe MSI Pro H610M-S DDR4, LGA1700, M-ATX, Chipset Intel H610, PRO-H610M-S-DDR4",
                499.99, R.drawable.h610msi));

        lista.add(new Produto("Placa Mãe Gigabyte A520M K V2",
                "Placa-Mãe Gigabyte A520M K V2 Rev. 1.0, AMD, Micro ATX, DDR4, Preto - A520M K V2",
                379.99, R.drawable.a520m));

        lista.add(new Produto("Placa Mãe ASUS TUF B760M-PLUS",
                "Placa-Mãe ASUS TUF GAMING B760M-PLUS WIFI II, Intel, DDR5, Preto - 90MB1HE0-M0EAY0",
                1249.99, R.drawable.b760m));

        lista.add(new Produto("Placa Mãe ASUS TUF B650M-E",
                "Placa-Mãe ASUS TUF Gaming B650M-E, WIFI, AMD AM5, B650, DDR5, Preto - 90MB1FV0-M0EAY0",
                1460.00, R.drawable.b650amd));

        lista.add(new Produto("Placa Mãe Gigabyte Z890 AORUS",
                "Placa Mãe Gigabyte Z890 AORUS XTREME AI TOP, Intel, E-ATX, DDR5, RGB, Wi-Fi 7, Bluetooth, Preto - 9AZ89XTAT-00",
                9299.99, R.drawable.z890));

        lista.add(new Produto("SSD Nvme Kingston M.2 500GB",
                "SSD Kingston Nv3 500 Gb Nvme M.2 2280 Leitura 5000mb/s Gravação 6000mb/s- Snv3s/500g",
                722.69, R.drawable.ssdkingston));


        lista.add(new Produto("Memoria Team Group 8GB DDR4 3200MHz",
                "Memoria Team Group T-Force Vulcan Pichau, 8GB (1x8), DDR4, 3200MHz, C16, Vermelha, TLPRD48G3200HC16F01",
                429.99, R.drawable.memoriaram8gb3200mhz));

        lista.add(new Produto("Fonte Mancer Thunder 400W 80 Plus",
                "Fonte Mancer Thunder 400W Bronze 80 Plus, MCR-THR400-BL01",
                159.99, R.drawable.fonte400w80plus));



        ProdutoAdapter adaptador = new ProdutoAdapter(lista, this.cart);

        produtosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        produtosRecyclerView.setHasFixedSize(true);
        produtosRecyclerView.setAdapter(adaptador);

//        ProdutoAdapter cartAdapter = new ProdutoAdapter(lista, this.cart);
//
//        cartProductsReciclerView.setLayoutManager(new LinearLayoutManager(this));
//        cartProductsReciclerView.setHasFixedSize(true);
//        cartProductsReciclerView.setAdapter(cartAdapter);

    }

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}