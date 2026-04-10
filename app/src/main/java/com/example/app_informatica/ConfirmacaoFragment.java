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
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout
        View view = inflater.inflate(R.layout.fragment_confirmacao, container, false);

        // 1. Receber os dados passados pelo CheckoutFragment (CEP e Pagamento)
        String cep = "Não informado";
        String pagamento = "Não informado";

        if (getArguments() != null) {
            cep = getArguments().getString("CEP", "Não informado");
            pagamento = getArguments().getString("PAGAMENTO", "Não informado");
        }

        // 2. Puxar os dados do Carrinho
        Carrinho cart = (Carrinho) Carrinho.getInstance();
        int totalItens = cart.getProducts().size();
        double totalPreco = 0;

        for (Produto p : cart.getProducts()) {
            totalPreco += p.preco; // Certifique-se de que o atributo de preço no seu Produto se chama "preco"
        }

        // Formatar o valor para Reais (R$)
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String totalFormatado = format.format(totalPreco);

        // 3. Gerar um número de pedido aleatório (entre 100000 e 999999)
        int numeroPedido = new Random().nextInt(900000) + 100000;

        // 4. Vincular os elementos do XML
        TextView txtPedidoNumero = view.findViewById(R.id.txtPedidoNumero);
        TextView txtMetodoPagamento = view.findViewById(R.id.txtMetodoPagamento);
        TextView txtEnderecoEntrega = view.findViewById(R.id.txtEnderecoEntrega);
        TextView txtQuantidadeItens = view.findViewById(R.id.txtQuantidadeItens);
        TextView txtTotalCompra = view.findViewById(R.id.txtTotalCompra);
        Button btnVoltarInicio = view.findViewById(R.id.btnVoltarInicio);

        // 5. Preencher as informações na tela
        txtPedidoNumero.setText("OBRIGADO! PEDIDO #" + numeroPedido);
        txtMetodoPagamento.setText("Método de pagamento: " + pagamento);
        txtEnderecoEntrega.setText("CEP de Entrega: " + cep);
        txtQuantidadeItens.setText("Itens: " + totalItens);
        txtTotalCompra.setText("Total: " + totalFormatado);

        // 6. Configurar o botão "Voltar para o Início"
        btnVoltarInicio.setOnClickListener(v -> {
            // Limpa o carrinho de compras
            cart.getProducts().clear();

            // Cria o caminho de volta para a tela principal
            // IMPORTANTE: Se a sua tela inicial tiver outro nome, troque MainActivity.class pelo nome correto
            android.content.Intent intent = new android.content.Intent(requireContext(), MainActivity.class);

            // Essas duas flags apagam todo o histórico de navegação (BackStack)
            intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Inicia a tela principal
            startActivity(intent);
        });

        return view;
    }
}