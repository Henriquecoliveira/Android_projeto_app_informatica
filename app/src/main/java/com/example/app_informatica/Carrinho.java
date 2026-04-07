package com.example.app_informatica;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrinho {

    private static Carrinho instance;
    private ArrayList<Produto> products;

    private Carrinho() {
        products = new ArrayList<>();
    }

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public ArrayList<Produto> getProducts() {
        return products;
    }

    public void addProduct(Produto product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    public void removeProduct(int position) {
        products.remove(position);
    }
}
