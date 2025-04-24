package br.com.invest7.ProjetoInvest7.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Acoes {
    private String nome_prod;
    private BigDecimal tx_ir;
    private BigDecimal preco_acao;
    private BigDecimal desvio;
}
