package com.dio.sistemapedidos.msprodutos.repository;

import com.dio.sistemapedidos.msprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
