package com.dio.sistemapedidos.mspedidos.controller;

import com.dio.sistemapedidos.mspedidos.client.ProdutoClient;
import com.dio.sistemapedidos.mspedidos.dto.PedidoRequestDTO;
import com.dio.sistemapedidos.mspedidos.dto.PedidoResponseDTO;
import com.dio.sistemapedidos.mspedidos.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private ProdutoClient produtoClient;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoRequestDTO request) {
        List<ProdutoDTO> produtos = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Long id : request.getProdutosIds()) {
            ProdutoDTO produto = produtoClient.findById(id);
            if (produto != null) {
                produtos.add(produto);
                if (produto.getPreco() != null) {
                    valorTotal = valorTotal.add(produto.getPreco());
                }
            }
        }
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setProdutos(produtos);
        response.setValorTotal(valorTotal);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/novopedido")
    public String criarNovoPedido(@RequestBody List<Long> produtoIds) {
        List<ProdutoDTO> produtos = produtoClient.getProdutos();
        List<String> nomes = produtos.stream()
                .filter(p -> produtoIds.contains(p.getId()))
                .map(ProdutoDTO::getNome)
                .collect(Collectors.toList());
        return "Pedido criado com os produtos: " + nomes;
    }
}
