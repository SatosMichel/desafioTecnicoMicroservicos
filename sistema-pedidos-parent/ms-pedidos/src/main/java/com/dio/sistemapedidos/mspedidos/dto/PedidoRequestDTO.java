package com.dio.sistemapedidos.mspedidos.dto;

import java.util.List;

public class PedidoRequestDTO {
    private List<Long> produtosIds;

    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }
}
