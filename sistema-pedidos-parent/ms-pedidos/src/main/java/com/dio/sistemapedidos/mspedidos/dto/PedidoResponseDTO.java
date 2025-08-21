package com.dio.sistemapedidos.mspedidos.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoResponseDTO {
    private List<ProdutoDTO> produtos;
    private BigDecimal valorTotal;

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
