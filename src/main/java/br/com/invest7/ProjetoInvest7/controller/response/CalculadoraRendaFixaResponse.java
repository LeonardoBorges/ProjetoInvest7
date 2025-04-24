package br.com.invest7.ProjetoInvest7.controller.response;

import java.math.BigDecimal;

public record CalculadoraRendaFixaResponse(
               String nome,
               BigDecimal totalInvestido,
               BigDecimal rendimentoBruto,
               BigDecimal impostoIR,
               BigDecimal rendimentoLiquido,
               BigDecimal valorTotal,
               String percentLucro
){
}
