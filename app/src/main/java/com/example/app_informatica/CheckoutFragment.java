package com.example.app_informatica;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class CheckoutFragment extends Fragment {

    public CheckoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);


        Spinner spinner = view.findViewById(R.id.spinnerPagamento);
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Pix");
        opcoes.add("Cartão Crédito");
        opcoes.add("Cartão Débito");
        opcoes.add("Boleto");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                opcoes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Carrinho cart = (Carrinho) Carrinho.getInstance();
        ImageView firstProductImage = view.findViewById(R.id.firstCardProduct);

        if(!cart.getProducts().isEmpty()){
            Produto firstProduct = cart.getProducts().get(0);
            firstProductImage.setImageResource(firstProduct.getImagemProduto());
        }


        Button btnConfirmar = view.findViewById(R.id.confirmar_Confirmacao);
        EditText edtCep = view.findViewById(R.id.endereco_Confirmacao); // Pega o campo de CEP

        btnConfirmar.setOnClickListener(v -> {

            String cepDigitado = edtCep.getText().toString();
            String metodoPagamento = spinner.getSelectedItem().toString();


            ConfirmacaoFragment confirmacaoFragment = new ConfirmacaoFragment();


            Bundle bundle = new Bundle();
            bundle.putString("CEP", cepDigitado);
            bundle.putString("PAGAMENTO", metodoPagamento);
            confirmacaoFragment.setArguments(bundle);


            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, confirmacaoFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}