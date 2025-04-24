package br.com.invest7.ProjetoInvest7.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculadoraFiisResponse {
    private String nomeProd;
    private BigDecimal precoFiis;
    private BigDecimal dividendYield;
    private BigDecimal qtdCotas;
    private BigDecimal dividendosMensais;
    private BigDecimal saldoCotas;
    private BigDecimal saldoDividendos;
}
