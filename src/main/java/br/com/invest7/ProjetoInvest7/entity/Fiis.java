package br.com.invest7.ProjetoInvest7.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Fiis {
    private String nome_prod;
    private BigDecimal tx_ir;
    private BigDecimal preco_fiis;
    private BigDecimal dividend_yeld;
    private BigDecimal desvio_cotas;
    private BigDecimal desvio_dividendos;
}
