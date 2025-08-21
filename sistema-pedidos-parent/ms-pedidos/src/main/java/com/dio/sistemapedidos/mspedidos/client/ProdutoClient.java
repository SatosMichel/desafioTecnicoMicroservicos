package com.dio.sistemapedidos.mspedidos.client;

import com.dio.sistemapedidos.mspedidos.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-produtos")
public interface ProdutoClient {
    @GetMapping("/produtos/{id}")
    ProdutoDTO findById(@PathVariable("id") Long id);

    @GetMapping("/produtos")
    List<ProdutoDTO> getProdutos();
}
