package br.com.invest7.ProjetoInvest7.controller.response;

import java.math.BigDecimal;

public record CalculadoraAcoesResponse(
        String nome,
        BigDecimal qtdAcoes,
        BigDecimal capital,
        BigDecimal saldoFinal,
        BigDecimal custoTotalCompra,
        BigDecimal valorTotalVenda,
        BigDecimal troco,
        BigDecimal precoAcao
) {
}
