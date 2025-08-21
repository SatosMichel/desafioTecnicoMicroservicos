package com.dio.sistemapedidos.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class AuthorizationHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        List<String> authHeaders = headers.get(HttpHeaders.AUTHORIZATION);

        if (authHeaders == null || authHeaders.isEmpty()) {
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        String authHeader = authHeaders.get(0);
        String expectedTokenValue = "meu-token-secreto";
        String bearerPrefix = "Bearer ";

        // Verifica se o cabeçalho começa com "Bearer "
        if (!authHeader.startsWith(bearerPrefix)) {
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        // Extrai apenas o token, removendo o "Bearer " e limpando espaços
        String token = authHeader.substring(bearerPrefix.length()).trim();

        // Compara apenas o valor do token
        if (!token.equals(expectedTokenValue)) {
            return onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}