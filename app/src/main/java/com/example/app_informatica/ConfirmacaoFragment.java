package com.example.app_informatica;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class ConfirmacaoFragment extends Fragment {

    public ConfirmacaoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmacao, container, false);


        String cep = "Não informado";
        String pagamento = "Não informado";

        if (getArguments() != null) {
            cep = getArguments().getString("CEP", "Não informado");
            pagamento = getArguments().getString("PAGAMENTO", "Não informado");
        }


        Carrinho cart = (Carrinho) Carrinho.getInstance();
        int totalItens = cart.getProducts().size();
        double totalPreco = 0;

        for (Produto p : cart.getProducts()) {
            totalPreco += p.preco;
        }


        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String totalFormatado = format.format(totalPreco);


        int numeroPedido = new Random().nextInt(900000) + 100000;

        TextView txtPedidoNumero = view.findViewById(R.id.txtPedidoNumero);
        TextView txtMetodoPagamento = view.findViewById(R.id.txtMetodoPagamento);
        TextView txtEnderecoEntrega = view.findViewById(R.id.txtEnderecoEntrega);
        TextView txtQuantidadeItens = view.findViewById(R.id.txtQuantidadeItens);
        TextView txtTotalCompra = view.findViewById(R.id.txtTotalCompra);
        Button btnVoltarInicio = view.findViewById(R.id.btnVoltarInicio);


        txtPedidoNumero.setText("OBRIGADO! PEDIDO #" + numeroPedido);
        txtMetodoPagamento.setText("Método de pagamento: " + pagamento);
        txtEnderecoEntrega.setText("CEP de Entrega: " + cep);
        txtQuantidadeItens.setText("Itens: " + totalItens);
        txtTotalCompra.setText("Total: " + totalFormatado);


        btnVoltarInicio.setOnClickListener(v -> {

            cart.getProducts().clear();


            android.content.Intent intent = new android.content.Intent(requireContext(), MainActivity.class);

            intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);


            startActivity(intent);
        });

        return view;
    }
}